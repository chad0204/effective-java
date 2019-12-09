package com.pc.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author pengchao
 * @since 13:59 2019-09-09
 */
public class CountDownLatchDemo1 {

    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for(int i = 0; i < concurrency; i++ ) {
            executor.execute(() -> {
                ready.countDown();//tell timer we're ready;
                try {
                    start.await();//Wait till peers are ready,等到并发都准备好
                    action.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            });
        }
        ready.await();//Wait for all workers to be ready
        long startNanos = System.nanoTime();
        start.countDown();//and they're off
        done.await();//Wait for all workers to finish

        return System.nanoTime() - startNanos;

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(time(Executors.newFixedThreadPool(10), 5, () -> System.out.println("1111")));
    }
}
