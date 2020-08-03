package com.pc.concurrent.threadPool;

import java.util.LinkedList;
import java.util.concurrent.*;

/**
 *
 * @author dongxie
 * @date 14:12 2019-12-07
 */
public class TestQueue {

    public static void main(String[] args) {
        /**
         * ArrayBlockingQueue和LinkedBlockingQueue都可以指定容量，超过会报错
         */
//        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
//        for(int i=0;i<11;i++) {
//            arrayBlockingQueue.add(i+"");
//        }
//        System.out.println(arrayBlockingQueue.size());

//        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
//        for(int i=0;i<11;i++) {
//            linkedBlockingQueue.add(i+"");
//        }
//        System.out.println(linkedBlockingQueue.size());

        /**
         * 无缓冲队列，即存即用
         */
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
//        for(int i=0;i<1;i++) {
//            synchronousQueue.add(1+"");
//        }
        synchronousQueue.offer("11");

        System.out.println(synchronousQueue.poll());

        System.out.println(synchronousQueue.size());



        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService cachedThreadPool1 = new ThreadPoolExecutor(0, 10,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(Thread.currentThread().getName()+"-rejectedExecution");
            }
        });//SynchronousQueue没有容量，即存即用


        for(int i=0;i<100;i++) {
            cachedThreadPool1.execute(() -> {

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());

            });
        }



    }
}
