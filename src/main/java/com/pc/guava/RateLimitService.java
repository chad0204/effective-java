package com.pc.guava;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试单机限流
 *
 *       限流操作
 *          1.首先过滤出vip用户，或者丢弃一些问题请求
 *          2.放入mq中削峰,超过一定数量就丢弃
 *          3.
 *
 *
 *
 * @author pengchao
 * @date 22:04 2020-07-09
 */
public class RateLimitService {


    private static AtomicInteger acquireCount = new AtomicInteger(0);
    private static final Object OBJECT = new Object();

    //每秒只发出50个令牌
    private  static RateLimiter rateLimiter = RateLimiter.create(50.0);


    private boolean tryAcquire() {
        // 等待1秒钟如果未能获取到许可证就返回false，否则返回true
        return rateLimiter.tryAcquire(1);

//        return rateLimiter.tryAcquire(1, 1000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        testSmoothwarmingUp();

    }


    public static void testSmoothBursty() throws InterruptedException {
        RateLimitService accessLimitService = new RateLimitService();
        ExecutorService executorService = Executors.newCachedThreadPool();


        System.out.println(rateLimiter.getRate());

        TimeUnit.SECONDS.sleep(10);//阻塞一会，让令牌桶攒满50个


        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    if (accessLimitService.tryAcquire()) {

                        if(System.currentTimeMillis()-start>1000) {
//                            System.out.println("==========超过1s=============");
                        } else {

                            //平滑限流，可以看出每20ms（1000/50）允许一次
                            System.out.println("获取许可证，执行业务逻辑。时间："+(System.currentTimeMillis()-start));

                            //计数
                            System.out.println("获取令牌统计"+acquireCount.incrementAndGet());
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException ex) {
                                //
                            }
                        }
                    } else {
                        if(System.currentTimeMillis()-start>1000) {
//                            System.out.println("==========超过1s=============");
                        } else {
                            System.err.println("未获取到许可证，请求可以丢弃。");
                        }

                    }
                }
            });
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }


    public static void testSmoothwarmingUp() {
        RateLimiter r = RateLimiter.create(10, 30, TimeUnit.SECONDS);
//        RateLimiter r = RateLimiter.create(5);
        while (true)
        {
            System.out.println("get 5 tokens: " + r.acquire(5) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
            /**
             * output:
             * get 1 tokens: 0.0s
             * get 1 tokens: 1.329289s
             * get 1 tokens: 0.994375s
             * get 1 tokens: 0.662888s  上边三次获取的时间相加正好为3秒
             * end
             * get 1 tokens: 0.49764s  正常速率0.5秒一个令牌
             * get 1 tokens: 0.497828s
             * get 1 tokens: 0.49449s
             * get 1 tokens: 0.497522s
             */
        }
    }




}
