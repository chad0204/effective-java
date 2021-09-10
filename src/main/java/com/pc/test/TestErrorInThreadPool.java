package com.pc.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-05-20 5:37 下午
 */
public class TestErrorInThreadPool {

    static ExecutorService service = Executors.newFixedThreadPool(8);

    public static void main(String[] args) {
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);

                    System.out.println(1);

                    throw new IllegalArgumentException("23333");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("over");



    }
}
