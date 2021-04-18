package com.pc.concurrent.collection;

import org.jetbrains.annotations.NotNull;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列
 *
 * @author pengchao
 * @date 19:34 2021-03-17
 */
public class TestDelayQueue {


    public static void main(String[] args) {


        DelayQueue<DelayedMessage> delayQueue = new DelayQueue<>();


        //启动消费线程，从queue中获取数据
        new Thread(new DelayQueueConsumer(delayQueue)).start();


        test1(delayQueue);

    }

    /**
     * 测试用例1：生成5条TTL时间依次增大的延迟消息：1秒，2秒，3秒，4秒，5秒
     * @param delayQueue 延迟队列
     */
    private static void test1(DelayQueue<DelayedMessage> delayQueue) {
        for (int i = 1; i <= 5; i++) {
            DelayedMessage delayedMessage = new DelayedMessage(String.valueOf(i), i*1000L);
            delayQueue.offer(delayedMessage);
        }
    }

    /**
     * 测试用例2：生成5条TTL时间依次减小的延迟消息：5秒，4秒，3秒，2秒，1秒
     * @param delayQueue 延迟队列
     */
    private static void test2(DelayQueue<DelayedMessage> delayQueue) {
        for (int i = 5; i > 0; i--) {
            String message = String.valueOf(i);
            DelayedMessage delayedMessage = new DelayedMessage(message, i*1000L);
            delayQueue.offer(delayedMessage);
        }
    }

    /**
     * 测试用例3：生成5个延迟时间随机的延迟消息
     * @param delayQueue 延迟队列
     */
    private static void test3(DelayQueue<DelayedMessage> delayQueue) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            // 生成1~10的随机数：作为1秒-10秒的延迟时间
            int ttl = 1 + random.nextInt(10);
            String message = String.valueOf(ttl);
            DelayedMessage delayedMessage = new DelayedMessage(message, ttl*1000L);
            delayQueue.offer(delayedMessage);
        }
    }








}

class DelayQueueConsumer implements Runnable {

    private final DelayQueue<DelayedMessage> delayQueue;

    public DelayQueueConsumer(DelayQueue<DelayedMessage> delayQueue) {
        this.delayQueue = delayQueue;
    }


    @Override
    public void run() {
        while(true) {
            try {
                // 从延迟队列的头部获取已经过期的消息
                // 如果暂时没有过期消息或者队列为空，则take()方法会被阻塞，直到有过期的消息为止
                DelayedMessage delayedMessage = delayQueue.take();
                System.out.println("Consumer received message: {}"+delayedMessage.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}



/**
 * 延迟消息实体
 */
class DelayedMessage implements Delayed {

    private String message;
    private long ttl;//任务到期时间


    public DelayedMessage(String message,long ttl) {
        setMessage(message);
        this.ttl = System.currentTimeMillis() + ttl;
    }

    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        // 计算该任务距离过期还剩多少时间
        long remaining = ttl - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }


    @Override
    public int compareTo(@NotNull Delayed o) {
        // 比较、排序：对任务的延时大小进行排序，将延时时间最小的任务放到队列头部
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
