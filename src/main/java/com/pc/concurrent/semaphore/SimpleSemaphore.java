package com.pc.concurrent.semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量，相当于许可，拿到许可才可执行。比如限制只能有五个线程同时进行。
 *
 *
 *      下面的实例的结果显示，一开始会有n个线程连续开始执行，然后释放一个进来一个，最后n个线程连续开始退出。
 *      说明同时执行的线程数为n.
 *
 *
 * 为什么不直接用线程池控制并发数量
 *      线程池自己维护线程数量，设置的线程数量就是最多执行的线程，没有执行的任务放在队列中进行调度。
 *      semaphore需要自己创建线程，创建多少个线程就有多少个线程，只是可同时执行的线程数量受到控制。
 *
 *      线程池控制线程数量（只有那么多线程），semaphore控制并发数量（有的线程阻塞未执行）。
 *
 *
 * @author dongxie
 * @date 09:44 2020-03-16
 */
public class SimpleSemaphore {

    private static final int n = 5;
    //创建一个数量为5的信号量
    private static Semaphore semaphore = new Semaphore(n,true); // 最大线程数




//    private static TryableSemaphore semaphore = new TryableSemaphoreActual(n); // 最大线程数


    public static void main(String[] args) {
        //不缓存任务的不限量数据库
        ExecutorService service = Executors.newCachedThreadPool();
        //开启20个线程
        for(int i=0;i<20;i++) {
            Thread t = new Thread(() -> {

                try {
                    //1.阻塞获取信号量，所有线程最后都会执行
//                    semaphore.acquire();// 如果线程超过5个,其他线程会阻塞到这来,知道线程执行结束释放许可

                    //2.尝试获取信号量，没有获取到许可的线程抛出异常
                    if(!semaphore.tryAcquire()) {
                        throw new InterruptedException("limited."+n);
                    }
//                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName()+"进来了");

                    //模拟实际业务逻辑
                    Thread.sleep((long) (Math.random() * 10000));


                    System.out.println(Thread.currentThread().getName()+"走了");

                    //释放信号量
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            service.execute(t);
        }

        service.shutdown();
    }
}
