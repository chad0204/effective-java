package com.pc.concurrent.collection.blockingqueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockArrayBlockQueue {
    private final String[] buffer;
    //锁
    private final ReentrantLock mainLock;
    //队列已经不满
    private final Condition notEmpty;
    //队列已经不为空
    private final Condition notFull;
    //size
    private int count = 0;

    public ReentrantLockArrayBlockQueue(int capacity) {
        this.buffer = new String[capacity];
        this.mainLock = new ReentrantLock();
        this.notEmpty = this.mainLock.newCondition();
        this.notFull = this.mainLock.newCondition();
    }

    public void put(String ele) throws InterruptedException {
        mainLock.lockInterruptibly();
        try {
            while (count == buffer.length) {
                System.out.println("队列已满");
                notFull.await();
            }
            buffer[count++] = ele;
            notEmpty.signal();
        } finally {
            mainLock.unlock();
        }
    }

    public boolean offer(String ele) {
        mainLock.lock();
        try {
            if (count == buffer.length) {
                return false;
            } else {
                buffer[count++] = ele;
                notEmpty.signal();
                return true;
            }
        } finally {
            mainLock.unlock();
        }
    }

    public boolean offer(String ele, long timeout, TimeUnit unit) {
        return false;
    }

    public String take() throws InterruptedException {
        mainLock.lockInterruptibly();
        try {
            while (count == 0) {
                System.out.println("队列已空");
                notEmpty.await();
            }
            count--;
            String val = buffer[count];
            notFull.signal();
            return val;
        } finally {
            mainLock.unlock();
        }
    }

    public String poll() {
        return null;
    }

    public String poll(long timeout, TimeUnit unit) {
        return null;
    }

    public int size() {
        mainLock.lock();
        try {
            return count;
        } finally {
            mainLock.unlock();
        }
    }
}
