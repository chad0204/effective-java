package com.pc.concurrent.collection.blockingqueue;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class TestArray {

    public static void main(String[] args) throws InterruptedException {

        SynchronizedArrayBlockQueue queue = new SynchronizedArrayBlockQueue(10);
//        ReentrantLockArrayBlockQueue queue = new ReentrantLockArrayBlockQueue(10);
        AtomicInteger count = new AtomicInteger();

        Thread p1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    queue.put("p1生产--->" + count.getAndIncrement());
                    sleep(10);
                }
            }
        });
        Thread p2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    queue.put("p2生产--->" + count.getAndIncrement());
                    sleep(10);
                }
            }
        });
        Thread p3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    queue.put("p3生产--->" + count.getAndIncrement());
                    sleep(10);
                }
            }
        });

        Thread c1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    System.out.println("c1消费--->" + queue.take() + ", 还剩:" + queue.size());
                    sleep(10);
                }
            }
        });
        Thread c2 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    System.out.println("c2消费--->" + queue.take() + ", 还剩:" + queue.size());
                    sleep(10);
                }
            }
        });
        Thread c3 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    System.out.println("c3消费--->" + queue.take() + ", 还剩:" + queue.size());
                    sleep(10);
                }
            }
        });

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();

        p1.join();
        p2.join();
        p3.join();
        c1.join();
        c2.join();
        c3.join();
    }
}
