package com.pc.concurrent.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock
 *
 * @author dongxie
 * @date 14:53 2020-05-12
 */
public class ThreadByTurnsForLock {

    private static AtomicInteger num = new AtomicInteger(1);

    private static Lock lock = new ReentrantLock();

    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();

    private static int flag = 1;


    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if(num.get()>99) {
                            return;
                        }
                        if (flag!=1) {
                            condition1.await();

                        }
                        System.out.println(Thread.currentThread().getName()+"--->"+num.getAndIncrement());
                        flag = 2;
                        condition2.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        },"Thread-1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (num.get() > 99) {
                            return;
                        }
                        if (flag != 2) {
                            condition2.await();
                        }
                        System.out.println(Thread.currentThread().getName() + "--->" + num.getAndIncrement());
                        flag = 3;
                        condition3.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

            }
        },"Thread-2");


        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();

                        if(num.get()>99) {
                            return;
                        }
                        if (flag!=3) {
                            condition3.await();
                        }

                        System.out.println(Thread.currentThread().getName()+"--->"+num.getAndIncrement());

                        flag = 1;
                        condition1.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        },"Thread-3");

        t1.start();

        t2.start();
        t3.start();

    }
}

