package com.pc.concurrent.future;

import com.google.common.collect.Maps;
import com.twodfire.share.result.Result;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *
 * @author pengchao
 * @date 15:41 2020-09-30
 */
public class AsyncFuture {


    static final ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(5,5, 0,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
    private static final Object lock = new Object();

    public static void main(String[] args) {

        Map<String, Future<String>> futureMap = Maps.newHashMap();

        Map<String, String> resultMap = Maps.newHashMap();


        AtomicInteger count = new AtomicInteger(0);
        for(int i=0;i<10;i++) {
            futureMap.put(i+"",threadPoolExecutor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {

                    TimeUnit.SECONDS.sleep(20);
                    synchronized (lock) {
                        count.incrementAndGet();
                        System.out.println(count.get());
                    }

                    return count.get()+"-over";
                }
            }));
        }


        System.out.println();

        futureMap.forEach((value, future) -> {

            try {
                resultMap.put(value,future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        });

        System.out.println(resultMap);

        threadPoolExecutor.shutdown();


    }


}
