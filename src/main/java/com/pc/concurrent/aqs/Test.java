package com.pc.concurrent.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author dongxie
 * @date 21:56 2020-03-24
 */
public class Test {


    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();


        lock.lock();

        System.out.println();


        reentrantReadWriteLock.writeLock().lock();

        reentrantReadWriteLock.readLock().lock();



    }
}
