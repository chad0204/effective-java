package com.pc.concurrent.synchronized_;

import org.openjdk.jol.info.ClassLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * -XX:BiasedLockingStartupDelay=0 关闭延迟偏向
 * -XX:-UseCompressedOops 是否开启压缩指针
 */
public class Markword {



    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(6000L);
        Object o = new Object();
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("==result==");

//        testBiased(o);
        testLight(o);
//        testWeight(o);


        System.out.println("==over==");
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());


    }

    public static void testBiased(Object o) throws InterruptedException {

        List<String> list = new ArrayList<>();

        Thread thread = new Thread(() -> {
            synchronized (o) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(ClassLayout.parseInstance(o).toPrintable());
//                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        });

        thread.start();
        thread.join();
    }


    public static void testLight(Object o) throws InterruptedException {

        List<String> list = new ArrayList<>();

        Thread thread = new Thread(() -> {
            synchronized (o) {
                list.add(ClassLayout.parseInstance(o).toPrintable());
//                System.out.println(ClassLayout.parseInstance(o).toPrintable());
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);


        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                list.add(ClassLayout.parseInstance(o).toPrintable());
//                System.out.println(ClassLayout.parseInstance(o).toPrintable());
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
        thread2.start();

        //交替执行
//        thread.start();
//        thread.join();//main等thread执行完才走下一步
//        Thread.sleep(1000L);
//        thread2.start();
//        thread2.join();
        Thread.sleep(1000L);

        System.out.println(list);


    }


    public static void testWeight(Object o) throws InterruptedException {
        List<String> list = new ArrayList<>();
        Thread thread = new Thread(() -> {
            synchronized (o) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(ClassLayout.parseInstance(o).toPrintable());
//                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(ClassLayout.parseInstance(o).toPrintable());
//                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        });

        thread2.start();
        thread.start();
        thread.join();
        thread2.join();
        Thread.sleep(10L);


        System.out.println(list);

    }
}
