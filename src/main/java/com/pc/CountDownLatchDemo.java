package com.pc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch latch = new CountDownLatch(10);//数值为10的计数器

    static final CountDownLatchDemo demo = new CountDownLatchDemo();


    @Override
    public void run() {
        try {
            //模拟10个执行时间不同的任务
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println(Thread.currentThread().getName()+"execute complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();//计数减一
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i=0;i<10;i++) {
//            executorService.execute(demo);
            executorService.submit(demo);//开启10个任务
        }

        //等待任务完成，才执行下面的操作
        latch.await();


        System.out.println("Bingo!");

        executorService.shutdown();


    }
}
