package com.pc.concurrent;

import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author pengchao
 * @since 14:22 2019-09-06
 */
public class ThreadStop {

    private static /*volatile*/ boolean stopRequested;


    private /*synchronized*/ static void setStopRequested() {//同步写，保证修改被立即写入内存
        stopRequested = true;
    }

    private synchronized static boolean getStopRequested() {//同步读，保证读取的一定是最新的
        return stopRequested;
    }


    public static void main(String[] args) throws InterruptedException {
        Thread addThread = new Thread(() -> {
            int i = 0;
            while (!getStopRequested()) {
                i++;
            }
        });

        addThread.start();

        TimeUnit.SECONDS.sleep(1);
        setStopRequested();
//        stopRequested = true;//这个修改对addThread线程来说是不可见的，addThread还是取的缓存中的值

    }
}
