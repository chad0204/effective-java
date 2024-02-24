package com.pc.lianlian.mevl.demo.queryclass;

import com.pc.lianlian.mevl.demo.ConditionExecutorContext;
import com.pc.lianlian.mevl.demo.FactorResult;
import com.pc.lianlian.mevl.demo.model.FactorInputParamConfigModel;
import com.pc.lianlian.mevl.demo.model.FactorModel;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pengchao
 * @since 2022/8/19 14:40
 */
public abstract class QueryService<T> {

    public FactorResult load(ConditionExecutorContext context,
                     FactorModel factor) {

        Map<String, Object> inputParamMap = factor.getInputParameterList()
                .stream().collect(Collectors.toMap(
                        FactorInputParamConfigModel::getParamName,
                        FactorInputParamConfigModel::getParamValue));

        T data = doQuery(inputParamMap, context.getActivity());

        //TODO 校验返回值类型
        if (!data.getClass().getName().equals(factor.getDataType())) {
            throw new IllegalArgumentException();
        }
        return FactorResult.builder().data(data).code("SUCCESS").build();
    }

    /**
     *
     * @param inputParamMap 配置的入参, 取值来自事件或者其他因子
     * @param activity 活动详情
     * @return
     */
    public abstract T doQuery(Map<String, Object> inputParamMap, String activity);
}
