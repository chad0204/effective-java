package com.pc.annotation.spring;

@FunctionalInterface
public interface Condition {

	boolean matches(String[] value);

}