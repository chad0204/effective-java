package com.pc.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pengchao
 * @date 18:59 2020-06-01
 */
public class Test {


    public static void main(String[] args) throws InterruptedException {

        //拦截两个线程，且两个线程得等barrierAction执行完，才开始执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("first over");
            }
        });

        new Thread(() -> {
            try {
                cyclicBarrier.await();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();


        TimeUnit.SECONDS.sleep(5);

        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(2);

    }
}
