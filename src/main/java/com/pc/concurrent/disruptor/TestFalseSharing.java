package com.pc.concurrent.disruptor;

import sun.misc.Contended;

import java.lang.annotation.ElementType;

/**
 * @author pengchao
 * @description: 测试伪共享
 * @date 2021-06-22 10:09 上午
 */
public class TestFalseSharing implements Runnable {

    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;

    private static ValueNoPadding[] longs;

    public TestFalseSharing(final int arrayIndex) {
        //当前线程访问的下标
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            System.gc();
            final long start = System.currentTimeMillis();
            runTest(i);
            System.out.println("Thread num " + i + " duration = " + (System.currentTimeMillis() - start));
        }

    }

    /**
     * 创建一个长度为n的数组，数组元素为保证可见性的变量
     * <p>
     * 对应创建n个线程，每个线程对应访问不同下标的元素，每个线程访问和更新该索引元素ITERATIONS次
     * <p>
     * 虽然每个线程访问的是不同索引对应的元素，但是线程越多，每个线程的访问时间却越长
     */
    private static void runTest(int NUM_THREADS) throws InterruptedException {
        //创建长度为n 的数组，填充保证可见性的共享变量
        longs = new ValueNoPadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValueNoPadding();
        }

        //创建 n个线程 ,对应访问不同的数组索引
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new TestFalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        //子线程结束前 主线程等待
        for (Thread t : threads) {
            t.join();
        }
    }


    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }


    /**
     * long -> volatile long -> padding long
     */
    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14, p15;
    }

//    @Contended //-XX:-RestrictContended
    public final static class ValueNoPadding {
//        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
//        protected long p9, p10, p11, p12, p13, p14, p15;

    }
}
