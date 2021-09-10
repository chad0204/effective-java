package com.pc.guava;

import com.github.rholder.retry.*;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-06-18 5:02 下午
 */
public class RetryTest {


    public static Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfException()
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .withWaitStrategy(WaitStrategies.randomWait(10, TimeUnit.SECONDS))
            .build();


    public static void main(String[] args) {


        try {
            retryer.call(() -> {
                // 逻辑处理
                test();
                return false;
            });
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        System.out.println("aa");

    }


    public static void test() {
        System.out.println("1");
        throw new IllegalArgumentException("错啦");
    }



}
