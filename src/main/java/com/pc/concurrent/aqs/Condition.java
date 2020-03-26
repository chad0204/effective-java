/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package com.pc.concurrent.aqs;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public interface Condition {


    /**
     * 使当前线程进入等待状态直到被通知(signal)或中断
     * 当其他线程调用signal()或signalAll()方法时，该线程将被唤醒
     * 当其他线程调用interrupt()方法中断当前线程
     * await()相当于synchronized等待唤醒机制中的wait()方法
     */
    void await() throws InterruptedException;


    /**
     * 当前线程进入等待状态，直到被唤醒，该方法不响应中断要求
     */
    void awaitUninterruptibly();

    /**
     * 调用该方法，当前线程进入等待状态，直到被唤醒或被中断或超时
     * 其中nanosTimeout指的等待超时时间，单位纳秒
     */
    long awaitNanos(long nanosTimeout) throws InterruptedException;

    /**
     * 同awaitNanos，但可以指明时间单位
     */
    boolean await(long time, TimeUnit unit) throws InterruptedException;

    /**
     * 调用该方法当前线程进入等待状态，直到被唤醒、中断或到达某个时
     * 间期限(deadline),如果没到指定时间就被唤醒，返回true，其他情况返回false
     */
    boolean awaitUntil(Date deadline) throws InterruptedException;


    /**
     * 唤醒一个等待在Condition上的线程，该线程从等待方法返回前必须
     *  获取与Condition相关联的锁，功能与notify()相同
     */
    void signal();

    /**
     * 唤醒所有等待在Condition上的线程，该线程从等待方法返回前必须
     * 获取与Condition相关联的锁，功能与notifyAll()相同
     */
    void signalAll();
}
