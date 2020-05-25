package com.pc.concurrent.collection;

import java.util.concurrent.*;

/**
 * TODO
 *
 * @author dongxie
 * @date 10:28 2020-05-16
 */
public class Test {

    public static void main(String[] args) {

        //lock
        BlockingQueue<String> array = new ArrayBlockingQueue<>(10);//必须有界

        BlockingQueue<String> link = new LinkedBlockingQueue<>();//有界，不传就是Integer.MAX_VALUE


        //cas
        ConcurrentLinkedDeque<String> con = new ConcurrentLinkedDeque<>();//无界

        LinkedTransferQueue<String> transferQueue = new LinkedTransferQueue<>();//无界

        //lock有界，且加锁性能差，cas无界，容易内存溢出

        //disruptor，有界无锁


    }
}
