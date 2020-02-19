package com.pc.book.chapter4.final_1;

/**
 * 一个不可变类
 *      final类的所有方法都是final方法，不可被继承
 *
 * @author dongxie
 * @date 11:10 2020-01-02
 */
public final class Demo {

    private int value;
    private final int finalValue;
    private static int staticFinalvalue = 10;

    public Demo(int finalValue) {
        this.finalValue = finalValue;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static Demo newDemo(int finalValue) {
        return new Demo(finalValue);
    }


}
