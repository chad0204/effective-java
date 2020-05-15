package com.pc.concurrent.threadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * 1.参数：
 * corePoolSize:线程最大并发数。当线程池中的线程数小于corePoolSize时，新提交的任务将创建一个新线程去执行，即使此时线程池中存在空闲线程。
 *              当线程池中的线程数大于corePoolSize时，且队列未满时新提交的任务将被放入workQueue中，等待线程池中的任务调度。
 * maximumPoolSize:队列满后，线程最大并发数(队列未满无效)。如果maximumPoolSize>corePoolSize(此时corePoolSize就是运行线程数,因为workQueue都满了）,
 *              新提交的任务会创建新线程执行，否则（未执行的任务已经占满队列，且线程已经开启到maximumPoolSize=corePoolSize），新的任务由
 *              RejectedExecutionHandler执行。
 * keepAliveTime:空闲线程多久被回收。当池中线程数超过corePoolSize，且超过这部分的线程空闲时间超过keepAliveTime则会被回收(当设置
 *               allowCoreThreadTimeOut(true)时，线程池中corePoolSize范围内的线程空闲时间达到keepAliveTime也将回收)。
 * TimeUnit:keepAliveTime的单位
 * workQueue:BlockQueue类型，可以指定有界或无界，缓存或非缓存
 * ThreadFactory:定义创建的线程的一些属性，如名称等。
 * RejectedExecutionHandler:拒绝策略（队列未满无效），任务数超出maximumPoolSize+workQueue时，任务交由RejectedExecutionHandler处理，
 *               当然如果是无界就不需要处理，等待调度好了。
 *
 *
 *
 * 2.队列
 * SynchronousQueue：没有容量的队列，不缓存任务，即存即用。要设置maximumPoolSizes无界（MAX_VALUE），确保不执行拒绝策略
 * LinkedBlockingQueue：缓冲队列，如果不设置容量，也就是队列不会满，maximumPoolSizes和拒绝策略就相当于无效
 * ArrayBlockingQueue：缓冲队列，必须设置容量，当线程数达到corePoolSize，并队列已满后，会创建新线程执行，当线程数量超过maximumPoolSizes执行拒绝策略
 *
 *
 *
 * 3.拒绝策略（无界队列不会触发拒绝策略）：
 * CallerRunsPolicy：调用者运行策略，只要线程池没有关闭(项目中一般也不会关闭)，就由提交任务的主线程处理，保证一定任务执行，但会阻塞影响性能。
 * AbortPolicy：终止策略，直接抛出终止异常，打断当前执行流程。默认的策略。
 * DiscardPolicy：丢弃策略，静悄悄的丢弃，基本不用。
 * DiscardOldestPolicy：弃老策略，如果线程池未关闭，就弹出队列头部的元素，然后尝试执行。也会丢弃任务，但丢弃的是老的和优先级低的任务。
 *
 *
 * 总结：
 *    无缓冲队列，maximumPoolSize最大，相当于来一个任务开一个线程。
 *    无界队列或者队列未满，最多创建corePoolSize线程，多余的任务进入workerQueue中等待调度，此时maximumPoolSize和拒绝策略无效。
 *    队列已满，最多创建maximumPoolSize个线程，超出线程数+队列任务数的任务会被拒绝策略处理。
 *
 *
 * 使用窍门：
 *  可以根据ThreadPoolExecutor#getQueue().size()来获取线程池中堆积的任务数，来控制程序
 *
 *
 *
 */
public class Test {

    //    static final int NCPU = Runtime.getRuntime().availableProcessors();

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    //创建corePoolSize个线程，后续任务存入队列中，如果队列满了则创建新线程，当线程总数量超过maximumPoolSize，也就是任务数量超过(队列长度+maximumPoolSize)时则执行拒绝策略
    //如果是无界队列，那么只创建corePoolSize个线程，超过线程数量的任务就放入队列中，maximumPoolSize和拒绝策略无效。
    private static final ExecutorService executorService =
            new ThreadPoolExecutor(3, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(2), r -> {
                Thread t = new Thread(r);
                t.setName("PC-Thread-" +atomicInteger.incrementAndGet());
                return t;
            }, new ThreadPoolExecutor.AbortPolicy());



    public static void main(String[] args) {

        //任务数<=maximumPoolSize+queue.size
        for(int i =0;i<13;i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"...start");
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        int nThreads = 10;
        int corePoolSize = 5;

        /**
         * 无最大线程数，无任务缓存，提交任务就开启线程执行或者获取任务执行，线程执行完60秒回收
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService cachedThreadPool1 = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());//SynchronousQueue没有容量，即存即用


        /**
         *  缓存队列，最大线程数为nThreads，因为无界maximumPoolSize和拒绝策略无效，执行完立即回收
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(nThreads);
        ExecutorService fixedThreadPool1 = new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        /**
         * 静态代理FinalizableDelegatedExecutorService，finalize析构函数进行线程池关闭，
         */
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        ExecutorService singleThreadPool1 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        ExecutorService scheduledThreadPool= Executors.newScheduledThreadPool(corePoolSize);
        ExecutorService scheduledThreadPool1=new ScheduledThreadPoolExecutor(corePoolSize);
//        new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
//                new ScheduledThreadPoolExecutor.DelayedWorkQueue());





    }
}
