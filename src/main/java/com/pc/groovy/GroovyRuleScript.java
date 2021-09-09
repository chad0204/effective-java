package com.pc.groovy;

import groovy.lang.Script;

import java.util.Map;

/**
 * @author tangyi
 * @date 18/3/14.
 */
public class GroovyRuleScript {

    private Script script;

    private Map<String, Object> ruleContext;

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public Map<String, Object> getRuleContext() {
        return ruleContext;
    }

    public void setRuleContext(Map<String, Object> ruleContext) {
        this.ruleContext = ruleContext;
    }
}
