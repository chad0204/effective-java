package com.pc.groovy.test;

import com.pc.groovy.GroovyCheckRuleEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengchao
 * @description: mixin方法导致的内存泄露
 * @date 2021-09-09 12:05 下午
 */
public class TestOOM {


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
}
