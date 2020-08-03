package com.pc.concurrent.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程交替执行
 *
 * 每个线程打印连续增长的三个数
 *
 * @author pengchao
 * @date 16:06 2020-06-11
 */
public class ThreeThreadTurns {

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();

    private static int flag = 1;

    private static int result = 0;


    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();

                        if(flag!=1) {
                            condition1.await();
                        }

                        if(result>=36) {
                            condition2.signal();
                            return;
                        }

                        for(int i=0 ; i< 3; i++) {
                            System.out.println(Thread.currentThread().getName()+":"+(++result));
                        }


                        condition2.signal();
                        flag = 2;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

            }
        },"Thread-1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();

                        if (flag != 2) {
                            condition2.await();
                        }

                        if(result>=36) {
                            condition3.signal();
                            return;
                        }

                        for (int i = 0; i < 3; i++) {
                            System.out.println(Thread.currentThread().getName()+":"+(++result));
                        }
                        condition3.signal();
                        flag = 3;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        },"Thread-2");

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();

                        if(flag!=3) {
                            condition3.await();
                        }

                        if(result>=36) {
                            return;
                        }

                        for(int i=0 ; i< 3; i++) {
                            System.out.println(Thread.currentThread().getName()+":"+(++result));
                        }
                        condition1.signal();
                        flag = 1;

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

            }
        },"Thread-3");


        thread1.start();
        thread2.start();
        thread3.start();



    }





}
