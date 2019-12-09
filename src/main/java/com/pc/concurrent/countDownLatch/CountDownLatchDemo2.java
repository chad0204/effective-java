package com.pc.concurrent.countDownLatch;

/**
 *
 * 自定义的计数器，有助于理解CountDownLatch
 *
 * @author pengchao
 * @since 14:39 2019-09-09
 */
public class CountDownLatchDemo2 {

    private int count;

    public CountDownLatchDemo2(int count) {
        if(count < 0) {
            throw new IllegalArgumentException();
        }
        this.count = count;
    }

    public void await() {
        for(;;) {
            synchronized (this) {
                if(count==0) {
                    return;
                }
            }
        }
    }

    public synchronized void countDown() {
        if(count!=0) {
            count--;
        }
    }

}
