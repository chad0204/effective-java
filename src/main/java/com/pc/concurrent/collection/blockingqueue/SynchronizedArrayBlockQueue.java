package com.pc.concurrent.collection.blockingqueue;


/**　
 * synchronized实现的缺点是不可中断
 * 由于没有condition, take和put阻塞都同一个条件队列上, 所以唤醒需要唤醒所有。
 */
public class SynchronizedArrayBlockQueue {
    private final String[] buffer;
    //size
    private int count = 0;

    public SynchronizedArrayBlockQueue(int capacity) {
        this.buffer = new String[capacity];
    }

    /**
     *
     *  C1
     *  C2
     *  P1
     *  P2
     *
     *  C1,C2发现队列为空，调用wait阻塞, 并释放锁
     *  P1获取锁，put元素，唤醒条件队列中的C1或C2中的一个，但是没有竞争过P1，P1又获取锁，直到队列已满，P1阻塞释放锁
     *  P2获取锁，发现队列已满，也阻塞。此时P1,P2和C1,C2全部阻塞
     *
     */
    public synchronized void put(String ele) throws InterruptedException {
        while (count == buffer.length) {
            System.out.println("队列已满");
            wait();
        }
        buffer[count++] = ele;
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (count == 0) {
            System.out.println("队列已空");
            wait();
        }
        count--;
        String val = buffer[count];
        notifyAll();
        return val;
    }

    public synchronized int size() {
        return count;
    }
}
