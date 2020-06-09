package com.pc.annotation.spring;

import java.lang.annotation.*;

/**
 *
 * @author pengchao
 * @date 22:32 2020-05-27
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ProfileCondition.class)
public @interface Profile {

    String[] value();

}
