package com.pc.book.chapter4.inherit;

import java.time.Instant;

/**
 * 子类
 *
 * @author dongxie
 * @date 10:04 2020-01-06
 */
public class Sub extends Super {

    private final Instant instant;

    public Sub() {
        instant = Instant.now();
    }

    @Override
    public void f() {
        //初始化父类构造器时会调用该覆盖方法，而此时子类还未初始化instant,打印null，如果使用instant就会NPE
        System.out.println(instant);
    }


    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.f();
    }



}
