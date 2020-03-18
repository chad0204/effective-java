package com.pc.concurrent.cas;


/**
 *
 *  使用synchronized表示cas语义
 *
 * @author pengchao
 * @since 10:05 2019-09-25
 */
class Number {

    private volatile int count;//用volatile的可见性，保证每次获得的都是内存最新值

    public Number(int count) {
        this.count = count;
    }

    public synchronized int get() {
        return count;
    }

    //AtomicInteger中native方法保证cas操作的原子性
    public synchronized boolean compareAndSwap(int expect, int newValue) {
        int old = this.count;
        if(old == expect) {
            this.count = newValue;
            return true;
        }
        return false;
    }


    public final int addAndGet(int delta) {
        for (;;) {
            int current = get();//获取内存最新值，作为期望值
            int next = current +delta;//准备修改成的值
            if(compareAndSwap(current, next)) {
                return next;
            }
        }
    }

}

public class Counter {

    private static  Number number = new Number(0);


    public static void main(String[] args) throws InterruptedException {


        for (int i = 0;i<2;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j =0;j<1000;j++) {
                       number.addAndGet(1);
                    }
                }
            }).start();
        }


        Thread.sleep(1000L);
        System.out.println(number.get());
    }



}
