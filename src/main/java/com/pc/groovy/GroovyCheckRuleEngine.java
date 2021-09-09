package com.pc.groovy;


import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author tangyi
 * @date 18/4/26.
 */

public class GroovyCheckRuleEngine {


    public static void main(String[] args) {

        GroovyCheckRuleEngine engine = new GroovyCheckRuleEngine();

        String msg = "{\n" +
                "\"id\":233,\n" +
                "\"name\":\"1111\"\n" +
                "}";

        String content = "\n" +
                "class EnhancedNumber {\n" +
                "\n" +
                "\tstatic def originalLeftShift = org.codehaus.groovy.runtime.DefaultGroovyMethods.&rightShift\n" +
                "\tstatic Number rightShift(Number self, Number operand) {\n" +
                "\t\tself.class == BigInteger ? self.shiftRight(operand) : originalRightShift(self, operand)\n" +
                "\t}\n" +
                "}\n" +
                "\n" +
                "Number.mixin(EnhancedNumber);"+
                "return msg;\n";



        for (;;) {
            Map<String,Object> param = new HashMap<>();
            param.put("msg",msg);
            Object res = engine.executeRule(content,param,"TEST");
        }




    }


    /**
     * Binding多线程并发问题；同一线程多次调用规则的上下文问题
     */
    private static ThreadLocal<Stack<Map<String, Object>>> ctxContainer = ThreadLocal.withInitial(() -> new Stack<Map<String, Object>>());

    private ThreadSafeBinding threadSafeBinding = new ThreadSafeBinding(ctxContainer);


    public Object executeRule(String ruleContent, Map<String, Object> paramMap, String scriptName) {
        if (StringUtils.isEmpty(ruleContent)) {
//            BizLogUtil.warn(log, "script为空，不向下执行:{}", scriptName);
            return null;
        }

        GroovyRuleScript groovyRule = GroovyRuleScriptParser.parse(ruleContent, paramMap, scriptName);
        if (groovyRule.getRuleContext().size()>0) {
            paramMap.putAll(groovyRule.getRuleContext());
        }
        return execute(groovyRule, paramMap);
    }

    private Object execute(GroovyRuleScript groovyRule, Map<String, Object> paramMap) {
        if (null != groovyRule && null != groovyRule.getScript()) {
            try {
                Script script = groovyRule.getScript();
                ctxContainer.get().push(paramMap);
                script.setBinding(threadSafeBinding);
                return script.run();
            } finally {
                if (!ctxContainer.get().isEmpty() && paramMap.size()>0) {
                    ctxContainer.get().pop();
                }
            }
        }
        return null;
    }

    private static class ThreadSafeBinding extends Binding {

        private ThreadLocal<Stack<Map<String, Object>>> usrCtxContainer = null;

        public ThreadSafeBinding(ThreadLocal<Stack<Map<String, Object>>> usrCtxContainer) {
            this.usrCtxContainer = usrCtxContainer;
        }

        @Override
        public Object getVariable(String name) {
            Map<String, Object> usr = null;
            Stack<Map<String, Object>> stack = usrCtxContainer.get();
            if (stack != null) {
                usr = stack.peek();
            }

            if (usr != null) {
                if (usr.containsKey(name)) {
                    return usr.get(name);
                }
            }

            return super.getVariable(name);
        }

        @Override
        public boolean hasVariable(String name) {
            Map<String, Object> usr = null;
            Stack<Map<String, Object>> stack = usrCtxContainer.get();
            if (stack != null) {
                usr = stack.peek();
            }

            if (usr != null && usr.containsKey(name)) {
                return true;
            }

            return false;
        }

        /**
         * 重写setVariable，否则有内存泄漏风险。在groovy脚本里，如果直接写【a=100】，前面不写def关键字，则会调用binding.setVariable方法，如果不重写该方法，
         * 则会调用super的setVariable方法， 会新建一个map，见groovy.lang.Binding.setVariable方法。这样本类一个binding的用法会导致super的变量永远无法回收而内存泄漏<br
         * />
         */
        @Override
        public void setVariable(String name, Object value) {
            // 得到当前上下文
            Map<String, Object> usr = null;
            Stack<Map<String, Object>> statck = usrCtxContainer.get();
            if (statck != null) {
                usr = statck.peek();
            }

            // 放入值
            if (usr != null) {
                usr.put(name, value);
            }
        }
    }
}

