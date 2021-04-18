package com.pc.concurrent.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计数器，控制线程执行，让五个线程都执行完才开始执行主线程
 */
public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch latch = new CountDownLatch(5);//数值为10的计数器

    static final CountDownLatchDemo demo = new CountDownLatchDemo();


    @Override
    public void run() {
        try {
            //模拟执行时间不同的任务
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println(Thread.currentThread().getName()+" execute complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();//线程结束计数减一,当计数变为0之后结束等待
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++) {
            executorService.execute(demo);
//            executorService.submit(dubbo);//开启10个任务
        }

        //等待任务完成，当count值减为0时，主线程才继续执行操作
        latch.await();

        System.out.println("Bingo!");

        executorService.shutdown();


    }
}
