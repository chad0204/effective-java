package com.pc.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-05-27 8:22 下午
 */
public class GuavaCache {

    private static ExecutorService executor = new ThreadPoolExecutor(
            2,
            2,
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(20),
            new ThreadPoolExecutor.CallerRunsPolicy());

    protected static long LOCAL_CACHE_TIMEOUT = 1;
    protected static Cache<String, Optional<String>> getValueCache = CacheBuilder.newBuilder()
            .expireAfterWrite(LOCAL_CACHE_TIMEOUT, TimeUnit.SECONDS)
            .maximumSize(2000)
            .concurrencyLevel(16)
            .build();


    public static String get(String key) {
        return null;
    }


    public static void main(String[] args) throws ExecutionException {


        for(int i=0;i<100;i++) {
            String res = get("aa", 100, new Supplier<String>() {
                @Override
                public String get() {
                    System.out.println(Thread.currentThread().getName() + ": db ops");
                    return "result";
                }
            });

        }
    }


    public static String get(String key, long ttl, Supplier<String> supplier) throws ExecutionException {
        String value;
        //先从redis缓存中获取数据，命中则返回
        value = get(key);

        if (value != null) {
            return value;
        } else {
            AtomicBoolean needUpdate = new AtomicBoolean(false);
            //缓存没命中，回调db
            Optional<String> r = getValueCache.get(key, () -> {
                String v = supplier.get();
                System.out.println("call");
                needUpdate.set(true);
                return Optional.ofNullable(v);
            });
            System.out.println("value");
            value = r.orElse(null);
            //回调成功，异步写入缓存
            if (needUpdate.get() && value != null) {

                System.out.println("set");
                String v = value;
                long start = System.nanoTime();
                // 需要在当前线程执行，否则context 信息丢失
                executor.execute(() -> {
                    long delay = System.nanoTime() - start;
                    // 判断时效性，防止异步延迟太久
                    if (delay * 2 < TimeUnit.SECONDS.toNanos(LOCAL_CACHE_TIMEOUT)) {
                        //setValue
                    }
                });
            }
        }
        return value;
    }

}
