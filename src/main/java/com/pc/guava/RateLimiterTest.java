package com.pc.guava;

import com.google.common.util.concurrent.RateLimiter;
import java.time.Duration;

/**
 * TODO
 *
 * @author pengchao
 * @date 16:35 2020-06-09
 */
public class RateLimiterTest {


    private static int a;



    static {
        a =1;
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(100, Duration.ofMillis(100));

        rateLimiter.acquire();


        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<1000;i++) {
            stringBuilder.append(i).append(",");

        }

        System.out.println(stringBuilder.toString());


    }
}
