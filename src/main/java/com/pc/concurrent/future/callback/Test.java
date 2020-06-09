package com.pc.concurrent.future.callback;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 *
 * @author dongxie
 * @date 11:21 2020-05-10
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureCallBack<String> futureCallBack = new FutureCallBack<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5L);
                System.out.println("call---"+System.currentTimeMillis());
                return "result-"+new Random().nextInt(10);
            }
        });

        //可以注册多个监听
        futureCallBack.addListener(new BiConsumer<String, Exception>() {
            @Override
            public void accept(String s, Exception e) {
                if (s != null) {
                    System.out.println("accept-"+System.currentTimeMillis()+"---"+s);
                } else {
                    e.printStackTrace();
                }

            }
        });


        new Thread(futureCallBack).start();

        System.out.println("get----"+System.currentTimeMillis()+"---"+futureCallBack.get());

        System.out.println("");

    }


}
