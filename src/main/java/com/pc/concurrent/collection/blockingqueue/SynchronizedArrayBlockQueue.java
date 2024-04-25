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
     *  C3
     *  C4
     *  P1
     *  P2
     *  队列只有两个元素
     *
     *  1. C1,C2,C3,C4依次获取锁由于队列为空，调用wait阻塞在条件队列，依次释放锁
     *  2. P1获取锁，put元素，notify只能唤醒条件队列中的C1、C2、C3或C4中的一个（比如C1）, 但是C1没有竞争过P1, P1又获取锁，put元素后唤醒C2, 此时队列已满, P1获取锁wait()阻塞， P2获取锁wait()阻塞，释放锁（此时C1和C2在同步队列，C3、C4、P1和P2都在等待队列）
     *  4. C1，C2已被在步骤2被移至同步队列，所以P2锁释放后会C1被唤醒（虚拟机唤醒）, C1消费完notify()唤醒C3, C3消费完notify()唤醒C4, C4发现队列已空阻塞释放锁，C3拿到锁队列为空阻塞，C2拿到锁队列为空阻塞,C1拿到锁队列为空阻塞
     *
     *  上面的问题出在C1，C2被移动到同步队列后，消费完一次只唤醒一个消费线程，正好只把两个等待队列的消费线程被唤醒。如果使用notifyAll，生产线程也会被移动到同步队列挣抢锁，则不会出现死锁情况
     *
     */
    public synchronized void put(String ele) throws InterruptedException {
        while (count == buffer.length) {
            System.out.println("队列已满");
            wait();
        }
        buffer[count++] = ele;
        notify();//notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (count == 0) {
            System.out.println("队列已空");
            wait();
        }
        count--;
        String val = buffer[count];
        notify();//notifyAll();
        return val;
    }

    public synchronized int size() {
        return count;
    }


    public static void main(String[] args) throws InterruptedException {
        SynchronizedArrayBlockQueue synchronizedArrayBlockQueue = new SynchronizedArrayBlockQueue(2);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while(true) {
                    try {
                        synchronizedArrayBlockQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Consumer+1");
                }
            }).start();
        }

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    synchronizedArrayBlockQueue.put("1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("product+1");
            }
        });
        thread.start();

        thread.join();

    }
}
