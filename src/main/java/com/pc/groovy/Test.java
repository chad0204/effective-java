package com.pc.groovy;


import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

public class Test {


    public static void main(String[] args) throws Exception {
        // GroovyScriptEngine的根路径，如果参数是字符串数组，说明有多个根路径
        GroovyScriptEngine engine = new GroovyScriptEngine("src/main/java/com/robot/universalrobot/groovyTest");
        Binding binding = new Binding();
        binding.setVariable("name", "软件质量保障");
        Object result1 = engine.run("demo_001.groovy", binding);
        Object result2 = engine.run("demo_003.groovy", binding);
    }


}
