package com.pc.concurrent.future;

import java.util.concurrent.TimeUnit;

/**
 * Thread.interrupt()就是给线程设置中断标记
 *
 * 1. 如果线程阻塞在sleep、join、wait等方法，设置中断标记的时机会导致上述方法抛出中断异常，并清空中断标记。
 * 2. 如果线程没有调用上述阻塞方法，或者只调用了LockSupport.park阻塞，可以通过t.isInterrupted()或者Thread.interrupted()来判断
 *    是否中断，并根据中断进行操作（如手动抛出中断异常）。静态方法会清楚中断标记。
 *
 *
 */
public class InterruptTest {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interrupt");
        t.interrupt();


    }
}
