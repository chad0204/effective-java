package com.pc.groovy;

import com.google.common.collect.Maps;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovySystem;
import groovy.lang.Script;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.util.Map;

public class GroovyRuleScriptParser {
    /**
     * 优化选项
     */
    private static Map<String, Boolean> optimizationOptions = null;

    private static Map<String, Script> scriptCache = Maps.newConcurrentMap();

    public GroovyRuleScriptParser() {
    }

    public static GroovyRuleScript parse(String content, Map<String, Object> paramMap, String scriptName) {
        try {
            Script script = scriptCache.get(content);
            if (script == null) {
                script = createScript(loadScript(content, scriptName));
                scriptCache.put(content, script);
            }
            if (null != script) {
                GroovyRuleScript groovyRule = new GroovyRuleScript();
                groovyRule.setScript(script);
                groovyRule.setRuleContext(paramMap);
                return groovyRule;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Script createScript(Class<?> scriptClass) throws IllegalAccessException,
            InstantiationException {
        Script script = null;
        GroovyObject object;
        object = (GroovyObject) scriptClass.newInstance();
        if (object instanceof Script) {
            script = (Script) object;
            // ------------------------------替换metaClass开始
            // 移除旧的，class一旦生成，便有了metaClass，见groovy.lang.Script的构造函数
            GroovySystem.getMetaClassRegistry().removeMetaClass(scriptClass);

            // script.setMetaClass(null);// 无效，会生成新的
            MetaClassWrapper newMetaClass = new MetaClassWrapper(script);
            script.setMetaClass(newMetaClass);
            // ------------------------------替换metaClass结束
        }
        return script;
    }

    private static Class<?> loadScript(String ruleText, String scriptName) {
        ClassLoader parent = GroovyRuleScriptParser.class.getClassLoader();
        GroovyClassLoader loader = null;

        // jdk7以上可打开indy提高执行效率，同时适配低版本情况
        float v = getCurrentJVMVersion();
        if (v >= 1.7) {
            // from http://www.infoq.com/cn/articles/new-groovy-20
            CompilerConfiguration config = new CompilerConfiguration();
            config.getOptimizationOptions().put("indy", true);
            loader = new GroovyClassLoader(parent, config);// 使用编译选项
        } else {
            if (optimizationOptions == null) {
                loader = new GroovyClassLoader(parent);// 默认优化选项
            } else {
                CompilerConfiguration config = new CompilerConfiguration();
                config.setOptimizationOptions(optimizationOptions);
                loader = new GroovyClassLoader(parent, config);// 使用编译选项 2013-09-03
            }
        }

        if (StringUtils.isEmpty(scriptName)) {
            // 开始编译
            return loader.parseClass(ruleText);
        } else {
            return loader.parseClass(ruleText, scriptName + "_" + System.currentTimeMillis() +
                    Math.abs(ruleText.hashCode()) + ".groovy");
        }
    }

    private static float getCurrentJVMVersion() {
        String v = System.getProperty("java.version");
        return Float.parseFloat(v.substring(0, 3));
    }
}
