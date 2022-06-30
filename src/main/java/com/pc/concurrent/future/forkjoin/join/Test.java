package com.pc.concurrent.future.forkjoin.join;


import java.util.concurrent.TimeUnit;

/**
 *
 *  声明t.join()的线程（是指声明这个t.join()的线程，不是指t线程），会让度cpu，直到t执行完成，才继续执行。
 *
 *  join 是通过object.wait实现的
 *
 *
 *  https://www.cnblogs.com/lcplcpjava/p/6896904.html
 *
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": running");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (;;) {
                System.out.println(Thread.currentThread().getName() + ": running");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread2.start();

        thread.join();//主线程等待thread执行完再执行。意思就是current执行到这里，thread插队进来执行，current得等着

        System.out.println(Thread.currentThread().getName() + "do something");






    }
}
