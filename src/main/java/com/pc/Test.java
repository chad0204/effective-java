package com.pc;

import java.time.LocalDate;
import java.util.concurrent.*;

/**
 *
 * @author dongxie
 * @date 10:24 2019-12-24
 */
public class Test {

    public static void main(String[] args) {


        int a = "abc".hashCode();



        LocalDate startDate = LocalDate.now().minusDays(90);

        ExecutorService executorService =
                new ThreadPoolExecutor(2,2,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());

        for(;;) {
            if(((ThreadPoolExecutor) executorService).getQueue().size()>100) {
                System.out.println(((ThreadPoolExecutor) executorService).getQueue().size());
                break;
            }
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"start");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
