package com.pc.inherit;

/**
 * TODO
 *
 * @author pengchao
 * @date 10:46 2020-08-03
 */
public class SonA extends Father {

    @Override
    public String say() {
        return "我是你儿子";
    }

    public void homework() {
        System.out.println("写作业");
    }
}
