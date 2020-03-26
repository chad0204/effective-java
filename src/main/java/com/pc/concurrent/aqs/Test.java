package com.pc.concurrent.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author dongxie
 * @date 21:56 2020-03-24
 */
public class Test {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();


        lock.lock();

        System.out.println();



    }
}
