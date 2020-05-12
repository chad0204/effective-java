package com.pc.concurrent.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 同步代码块
 *
 * @author dongxie
 * @date 14:29 2020-05-12
 */
public class ThreadByTurnsSyncBlock {

    private static AtomicInteger i = new AtomicInteger(1);
    public static void main(String[] args) {
        Object o = new Object();
        Thread t1 = new Thread(new ByTurns(i,o),"Thread-1");
        Thread t2 = new Thread(new ByTurns(i,o),"Thread-2");
        t1.start();
        t2.start();
    }
}


class ByTurns implements Runnable {
    private AtomicInteger atomicInteger;
    private final Object lock;
    public ByTurns(AtomicInteger atomicInteger, Object lock) {
        this.atomicInteger = atomicInteger;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()+"--->"+atomicInteger.getAndIncrement());
                lock.notify();
                if(atomicInteger.get()>=100) {
                    return;
                }
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
