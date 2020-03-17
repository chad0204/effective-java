package com.pc.concurrent.volatile_;


/**
 * 非并发情况下，结果只有两种：a=1,b=2 或者 a=3,b=3
 *
 * 并发出现两种异常情况：
 * a=3,b=2, 因为线程A执行a=3挂起，此时线程B执行print,这是volatile无法避免的。
 * b=3,a=1, 因为线程A修改a=3之后，对线程B不可见，b的缓存中依然是a=1,导致b=a=1。
 *
 * @author dongxie
 * @date 17:40 2020-03-17
 */
public class VolatileTest {


    private static boolean flag = false;
    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag) {
                    f();
                }
                System.out.println("thread over");
            }
        }).start();

        //一秒后关闭线程
        Thread.sleep(1000);
        flag = true;
        System.out.println("main over");

    }

    private static void f() {
//        System.out.println();//调用同步方法或native会刷新内存，也会缓存失效
    }

}
