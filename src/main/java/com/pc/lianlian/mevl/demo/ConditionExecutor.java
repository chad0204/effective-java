package com.pc.lianlian.mevl.demo;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.pc.lianlian.mevl.demo.model.FactorModel;
import com.pc.lianlian.mevl.demo.queryclass.BillService;
import com.pc.lianlian.mevl.demo.queryclass.ProductService;
import com.pc.lianlian.mevl.demo.queryclass.UserService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.Pair;
import org.mvel2.MVEL;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 *
 * 条件维度的执行
 *
 * @author pengchao
 * @since 2022/8/17 17:17
 */
public class ConditionExecutor {

    public boolean executor(ConditionExecutorContext ruleConditionContext) {
        //加载因子
        Boolean execute = loadFactors(ruleConditionContext);
        if (!execute) {
            return Boolean.FALSE;
        }

        System.out.println("因子加载完成: " + JSON.toJSONString(ruleConditionContext));
        //执行最终表达式
        return (boolean) MVEL.eval(
                ruleConditionContext.getRuleConditionModel().getRuleConditionMvel(),
                ruleConditionContext.getParamMap()
        );
    }


    /**
     * 并行执行 中断执行
     * @param ruleConditionContext
     * @return
     */

    private volatile boolean suspend = false;

    private Boolean loadFactors(ConditionExecutorContext ruleConditionContext) {

        List<FactorModel> parallel = ruleConditionContext.getRuleConditionModel().getFactorList().stream()
                .filter(factorModel -> factorModel.getPriority() < 0)
                .filter(factorModel -> StringUtils.isEmpty(factorModel.getPostProcessorExp()))
                .collect(Collectors.toList());

        //整体阻塞执行
        //拿到所有结果
        parallelConsume(parallel.size(), parallel, new Consumer<FactorModel>() {
            @Override
            public void accept(FactorModel factor) {


                parseParamValue(ruleConditionContext.getParamMap(), factor);
                loadFactor(ruleConditionContext, factor);
                if (StringUtils.isNotEmpty(factor.getPostProcessorExp())) {
                    //后置处理器
                    Boolean res = (Boolean) MVEL.eval(factor.getPostProcessorExp(), ruleConditionContext.getParamMap());
                    if (!res) {
                        System.out.println("后置处理器校验失败: 输出因子和上下文" + factor);
                    }
                }

            }
        });


        for (FactorModel factor: ruleConditionContext.getRuleConditionModel().getFactorList()) {
            //解析入参的值, 实现因子加载结果复用
            parseParamValue(ruleConditionContext.getParamMap(), factor);
            loadFactor(ruleConditionContext, factor);
            if (StringUtils.isNotEmpty(factor.getPostProcessorExp())) {
                //后置处理器
                Boolean res = (Boolean) MVEL.eval(factor.getPostProcessorExp(), ruleConditionContext.getParamMap());
                if (!res) {
                    System.out.println("后置处理器校验失败: 输出因子和上下文" + factor);
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    private void parseParamValue(Map<String, Object> paramMap, FactorModel factor) {
        factor.getInputParameterList().forEach(param -> {
            if (StringUtils.isNotEmpty(param.getParseExpression())) {
                //通过参数解析器从上下文中获取
                Object value = MVEL.eval(param.getParseExpression(), paramMap);
                if (value == null) {
                    throw new IllegalArgumentException();
                }
                if (!value.getClass().getName().equals(param.getParamType())) {
                    throw new IllegalArgumentException();
                }
                param.setParamValue(value);
            } else {
                //直接从上下文参数中获取（没有入参表达式说明可以直接从事件中获取或者属于营销系统变量，系统变量
                // 不用设置入参，因子加载方法中从activity中获取）
                param.setParamValue(paramMap.get(param.getParamName()));
            }
        });
    }

    /**
     * 需要入参的类型和返回值的类型嘛
     * @param ruleConditionContext
     * @param factor
     */
    private void loadFactor(ConditionExecutorContext ruleConditionContext, FactorModel factor) {
        if (factor.getDataSourceType().equals(FactorTypeEnum.PROCESSED.getCode())) {
            //匹配注入的类
            String queryClass = factor.getQueryClass();

            FactorResult result;
            if (queryClass.equals("userService")) {
                result = new UserService().load(ruleConditionContext, factor);
            } else if (queryClass.equals("productService")){
                result = new ProductService().load(ruleConditionContext, factor);
            } else {
                result = new BillService().load(ruleConditionContext, factor);
            }
            ruleConditionContext.getParamMap().put(factor.getFactorAliasName(), result.getData());
        } else {
            //TODO 探头因子, 不用维护, 如果是事件参数则已存在于paramMap, 如果是活动参数,包含在activity中。
            // 比如 当前apollo配置的B2C_PLATFORM_ID_LIST, productCodeList这些可以在条件中设置
            Map<String, Object> eventParams = ruleConditionContext.getParamMap();
        }

    }


    private static  <T> void parallelConsume(int parallelism, Collection<T> collection, Consumer<T> consumer) {
//        log.info("Create pool with size : " + parallelism);
        ForkJoinPool pool = new ForkJoinPool(1024);
        try {
            pool.invoke(ForkJoinTask.adapt(() -> collection.parallelStream().forEach(consumer)));
        } finally {
            pool.shutdown();
        }
    }



    protected static final AtomicInteger completedCount = new AtomicInteger(0);

    private static Random random = new Random(47);

    protected final static CopyOnWriteArrayList<Pair<FactorModel, String>> fails = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        List<FactorModel> list = Lists.newArrayList(
                FactorModel.builder().factorAliasName("aaa").build(),
                FactorModel.builder().factorAliasName("bbb").build(),
                FactorModel.builder().factorAliasName("ccc").build(),
                FactorModel.builder().factorAliasName("ddd").build(),
                FactorModel.builder().factorAliasName("eee").build());


        StopWatch sw = StopWatch.createStarted();
        parallelConsume(list.size(), list, new Consumer<FactorModel>() {
            @SneakyThrows
            @Override
            public void accept(FactorModel factorModel) {
                try {
                    int i = random.nextInt(19);
                    if (i > 1) {
                        throw new IllegalArgumentException("太大了");
                    }
                    TimeUnit.SECONDS.sleep(i);
                    System.out.println(Thread.currentThread().getName() + " 执行因子 " +factorModel.getFactorAliasName() + ", 耗时: " + i);
                    completedCount.incrementAndGet();
                } catch (Exception e) {
                    fails.add(Pair.of(factorModel, e.getMessage()));
                }
            }
        });

        sw.stop();

        System.out.println("总耗时：" + sw.getTime());
    }

}
