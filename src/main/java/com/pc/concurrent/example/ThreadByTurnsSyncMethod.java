package com.pc.concurrent.example;

/**
 * 直接用当前对象作为锁
 *
 * @author dongxie
 * @date 15:36 2020-05-12
 */
public class ThreadByTurnsSyncMethod {

    private static int num = 1;

    public static void main(String[] args) {

        ThreadByTurnsSyncMethod threadByTurnsMethod = new ThreadByTurnsSyncMethod();
        new Thread(threadByTurnsMethod::printNum,"Thread-1").start();
        new Thread(threadByTurnsMethod::printNum,"Thread-2").start();

    }

    public synchronized void printNum() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName()+"--->"+num++);
                if(num>99) {
                    notify();
                    return;
                }
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
