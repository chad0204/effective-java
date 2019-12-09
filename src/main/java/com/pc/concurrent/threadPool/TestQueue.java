package com.pc.concurrent.threadPool;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 *
 * @author dongxie
 * @date 14:12 2019-12-07
 */
public class TestQueue {

    public static void main(String[] args) {
        /**
         * ArrayBlockingQueue和LinkedBlockingQueue都可以指定容量，超过会报错
         */
//        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
//        for(int i=0;i<11;i++) {
//            arrayBlockingQueue.add(i+"");
//        }
//        System.out.println(arrayBlockingQueue.size());

//        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(10);
//        for(int i=0;i<11;i++) {
//            linkedBlockingQueue.add(i+"");
//        }
//        System.out.println(linkedBlockingQueue.size());

        /**
         * 无缓冲队列，即存即用
         */
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
//        for(int i=0;i<1;i++) {
//            synchronousQueue.add(i+"");
//        }
        synchronousQueue.offer("11");

        System.out.println(synchronousQueue.poll());

        System.out.println(synchronousQueue.size());

    }
}
