package com.pc.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 *
 *  CyclicBarrier和countDownLatch一样，都是让指定线程等待在一个点。
 *  但是CyclicBarrier可以重用，CyclicBarrier还可以传入优先执行的线程。
 *
 * @author pengchao
 * @date 18:59 2020-06-01
 */
public class Test {


    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        //拦截两个线程，且两个线程得等barrierAction执行完，才开始执行
        CyclicBarrier cyclicBarrierWithAction = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("action doing");
            }
        });

//        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);



        for(int i=0;i<10;i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"-waiting1");
                    TimeUnit.SECONDS.sleep(1);
                    cyclicBarrierWithAction.await();//满足10个后释放

                    System.out.println(Thread.currentThread().getName()+"-waiting2");
                    cyclicBarrierWithAction.await();//释放后还可以继续使用。

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"-ending");
            },"threadA"+i).start();
        }

        //这里不会阻塞，已经有10个线程了
//        cyclicBarrierWithAction.await();
        System.out.println("main ending");
    }
}
