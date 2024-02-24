package com.pc.concurrent.volatile_;

public class VolatileNotAtomicExample {
    private volatile int counter = 0;

    //这里用synchronized修饰保证原子性
    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        final VolatileNotAtomicExample example = new VolatileNotAtomicExample();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    example.increment(); // 对 volatile 变量进行递增操作
                }
            }).start();
        }

        // 等待所有线程执行完成
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + example.getCounter()); // 打印最终的计数值
    }
}

