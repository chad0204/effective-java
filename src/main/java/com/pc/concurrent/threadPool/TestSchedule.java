package com.pc.concurrent.threadPool;

import java.util.concurrent.*;

/**
 * 定时任务
 *
 * @author pengchao
 * @date 14:11 2020-06-09
 */
public class TestSchedule {



    private static final ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(3);

    public static void main(String[] args) {

        //延时
        scheduled.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule");
            }
        },5, TimeUnit.SECONDS);

        //周期
        scheduled.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("scheduleWithFixedDelay");
            }
        },10,5,TimeUnit.SECONDS);




    }
}
