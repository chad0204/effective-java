package com.pc.concurrent.collection;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞队列
 *
 * @author dongxie
 * @date 11:23 2020-03-28
 */
public class TestBlockQueue {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static final ExecutorService executorService =
            new ThreadPoolExecutor(1, 10, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5), r -> {
                Thread t = new Thread(r);
                t.setName("PC-Thread-" +atomicInteger.incrementAndGet());
                return t;
            }, new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {


        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);

        executorService.execute(new Producer(blockingQueue));
        executorService.execute(new Producer(blockingQueue));
        executorService.execute(new Consumer(blockingQueue));

//        executorService.submit(new Producer(blockingQueue));
    }

}

class Producer implements Runnable {

    private final BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                blockingQueue.put(produce());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private String produce() {
        String s = "生产产品："+new Random().nextInt(1000);
        System.out.println(s);
        return s;
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void consume(String take) {
        System.out.println("消费产品："+take);
    }
}


