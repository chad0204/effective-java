package com.pc.concurrent.semaphore;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * hystrix中实现的简单信号量
 *  不支持阻塞
 *  不支持公平
 *
 * @author dongxie
 * @date 14:25 2020-03-17
 */
interface TryableSemaphore {
    boolean tryAcquire();

    void release();

    int getNumberOfPermitsUsed();
}


public class TryableSemaphoreActual implements TryableSemaphore {

    private Integer numberOfPermits;

    public TryableSemaphoreActual(Integer numberOfPermits) {
        this.numberOfPermits = numberOfPermits;
    }

    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public boolean tryAcquire() {
        int currentCount = count.incrementAndGet();
        if (currentCount > numberOfPermits) {
            count.decrementAndGet();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void release() {
        count.decrementAndGet();
    }

    @Override
    public int getNumberOfPermitsUsed() {
        return count.get();
    }
}


