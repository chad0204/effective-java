package com.pc.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 * cas是为了保证并发修改时，线程能获取到其他线程最新修改的值，保证每次线程操作执行的结果累计。
 *
 * ABA问题，使用AtomicStampedReference，每次操作版本号会变化，比较的时候不仅比较值，还会比
 * 较版本号。如果值相同，版本号不同，说明出现aba，可以选择是放弃操作，也就是出现aba则放弃当前线程操作。
 * 或者自旋，重新获取值和版本号，进行对比，没有出现并发修改或者aba则会修改成功。
 *
 *
 * @author dongxie
 * @date 22:06 2020-03-17
 */
public class Test {

    private static int count1 = 0;

    private static AtomicInteger count2 = new AtomicInteger(0);

    private static AtomicStampedReference<Integer> count3 = new AtomicStampedReference<>(0, 0);


    public static void main(String[] args) throws InterruptedException {
        for(int i =0;i<1;i++) {
            f();
        }
    }

    public static void f() throws InterruptedException {
        //结果可能不是0
        for(int i =0;i<2;i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j =0;j<100;j++) {
                    count1++;
                }
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j =0;j<100;j++) {
                    count1--;
                }
            }).start();

        }

        //结果一定是0，但是存在aba问题
        for(int i =0;i<2;i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j =0;j<100;j++) {
                    count2.incrementAndGet();
                }
            }).start();
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j =0;j<100;j++) {
                    count2.decrementAndGet();
                }
            }).start();
        }




        //结果一定是0，但是记录操作了400次，避免aba问题，（偶尔死锁），保证每次只会被执行一次。
        for(int i =0;i<2;i++) {

            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j =0;j<100;j++) {
//                    int ref;
//                    int stamp;
//                    do {
//                        ref = count3.getReference();
//                        stamp = count3.getStamp();
//                    } while(!count3.compareAndSet(ref,ref+1,stamp,stamp+1));

                    while (true) {
                        int ref = count3.getReference();
                        int stamp = count3.getStamp();
                        if(count3.compareAndSet(ref,ref+1,stamp,stamp+1)) {
                            break;
                        }
                    }
                }
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j =0;j<100;j++) {
//                    int ref;
//                    int stamp;
//                    do {
//                        ref = count3.getReference();
//                        stamp = count3.getStamp();
//                    } while(!count3.compareAndSet(ref,ref-1,stamp,stamp+1));

                    while (true) {
                        int ref = count3.getReference();
                        int stamp1 = count3.getStamp();
                        if(count3.compareAndSet(ref,ref-1,stamp1,stamp1+1)) {
                            break;
                        }
                    }
                }
            }).start();
        }

        Thread.sleep(2000);

        System.out.println("count1:"+count1);
        System.out.println("count2:"+count2.get());
        System.out.println("count3:"+count3.getReference()+",stamp:"+count3.getStamp());
    }
}
