package com.pc.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.SneakyThrows;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author pengchao
 * @since 2022/8/12 16:44
 */
public class Test {

    private static Cache<String, String> CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .maximumSize(2000)
            .concurrencyLevel(1)
            .build();


    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                String s = CACHE.get("key-"/* + new Random().nextInt(10000)*/, new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        String value = "abcde";
                        threadLocal.set(value);
                        return threadLocal.get();
                    }
                });
                System.out.println(s);
            }
        }).start();


        System.out.println(threadLocal.get());


        for (int i=0; i < 100; i++) {
            new Thread(() -> {
                try {
                    CACHE.get("key-"/* + new Random().nextInt(10000)*/, new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            System.out.println(Thread.currentThread().getName());
                            TimeUnit.SECONDS.sleep(10);
                            return "null";
                        }
                    });
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();

        }

        TimeUnit.SECONDS.sleep(1000);



    }
}
