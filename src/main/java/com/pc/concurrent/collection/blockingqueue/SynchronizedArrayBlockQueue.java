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

    public synchronized void put(String ele) throws InterruptedException {
        while (count == buffer.length) {
            System.out.println("队列已满");
            wait();
        }
        buffer[count++] = ele;
        //唤醒必须用notifyAll(), 否则会死锁
        //队列为空c0,c1都阻塞, 此时p0执行完毕, notify()只能唤醒一个线程, 如果每次唤醒的还是p0, 直到队列满, p0也阻塞。
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
