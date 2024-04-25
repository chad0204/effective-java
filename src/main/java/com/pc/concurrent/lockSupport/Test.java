package com.pc.concurrent.lockSupport;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * @author pengchao
 * @date 09:02 2020-05-27
 */
public class Test {

    /**
     * 测试park响应中断但是不会抛出异常
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("park线程->" + Thread.currentThread().getName() + ", 当前中断状态: " + Thread.currentThread().isInterrupted());
                LockSupport.park();
                System.out.println("park线程响应中断" + Thread.currentThread().getName() + ", 当前中断状态: " + Thread.currentThread().isInterrupted());
            }
        });
        thread1.start();


        Thread thread2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("查看线程->" + thread1.getName() + ", 当前中断状态: " + thread1.isInterrupted());
                thread1.interrupt();
                System.out.println("中断线程->" + thread1.getName());
            }
        });
        thread2.start();


        thread1.join();
        thread2.join();

    }
}
