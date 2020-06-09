package com.pc.annotation.spring;


/**
 *
 * @author pengchao
 * @date 22:29 2020-05-27
 */
public class ProfileCondition implements Condition {
    @Override
    public boolean matches(String[] values) {
        //只要有dev则校验通过
        for(String value :values) {
            if("dev".equals(value)) {
                return true;
            }
        }
        return false;
    }
}
