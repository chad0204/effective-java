package com.pc.concurrent.synchronized_.timoutDemo;

import java.util.LinkedList;

public class ConnectionPool {
    private final LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize <= 0) {
            return;
        }
        for (int i = 0; i < initialSize; i++) {
            pool.add(new Connection((long) i));
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        synchronized (pool) {
            pool.addLast(connection);
            pool.notifyAll();
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long base = System.currentTimeMillis();
                long remain = mills;
                while (pool.isEmpty() && remain > 0) {
                    pool.wait(remain);
                    remain = mills - (System.currentTimeMillis() - base);
                }
                if (!pool.isEmpty()) {
                    return pool.removeFirst();
                }
                return null;
            }
        }
    }
}
