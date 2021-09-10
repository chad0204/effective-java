package com.pc.concurrent.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-06-17 10:22 上午
 */
public class TestQueueSize {


    private static final ThreadPoolExecutor LOAD_SOURCE_EXECUTOR = new ThreadPoolExecutor(2, 2, 60L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactoryBuilder().setNameFormat("offlineTaskProcessor-load-source-%d").build());


    public static void main(String[] args) {

        for (int i=0;i<50;i++) {

            if (LOAD_SOURCE_EXECUTOR.getQueue().size()>10) {
                System.err.println("当前任务过多"+ LOAD_SOURCE_EXECUTOR.getQueue().size());
                continue;
            }

            LOAD_SOURCE_EXECUTOR.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println(Thread.currentThread().getName()+"over");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
