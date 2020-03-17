package com.pc.concurrent.volatile_;

/**
 *
 * 内存模型
 *      线程 高速缓存 内存
 *
 * 原理：
 *      对volatile变量的写会立即刷新到内存（lock前缀指令）。
 *      缓存写回内存会使其他缓存中的值无效（lock前缀指令）。
 *
 * 可见性：对volatile的读一定能看到任意线程对volatile的最后一次写，读最新。（lock前缀）
 * 原子性：对volatile变量的单独写和读都具有原子性，但是复合操作不具备原子性，如volatile++
 * 有序性：禁止重排序，如构造器的三步操作，分配内存地址，初始化对象，对象指向内存。（内存屏障）
 *
 *
 * @author dongxie
 * @date 14:43 2020-03-17
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        VolatileFeaturesExample1 volatileFeaturesExample = new VolatileFeaturesExample1();

        //线程A根据条件一直运行
        new Thread(() -> {
            while (volatileFeaturesExample.get()==0) {
                //业务
//                System.out.println(1);//同步方法或者native方法会刷新内存
            }
            System.out.println("thread over");
        }).start();

        Thread.sleep(1000);

        //线程B设置条件，停止线程A
        new Thread(() -> {
            volatileFeaturesExample.set(1);
            System.out.println("set 1");
        }).start();

    }
}

/**
 * 读/写都不具备原子性
 */
class VolatileFeaturesExample1 {

    private long v1 = 0L;

    public void set(long i) {
        v1 = i;
    }

    public long get() {
        return v1;
    }

    public long getAndIncrement() {
        return v1++;
    }
}

/**
 * 读/写具备原子性，复合操作不具备原子性
 */
class VolatileFeaturesExample2 {

    private volatile long v1 = 0L;

    public void set(long i) {
        v1 = i;
    }

    public long get() {
        return v1;
    }

    public long getAndIncrement() {//
        return v1++;
    }
}

/**
 * volatile相当于下面的代码
 */
class VolatileFeaturesExample3 {

    private long v1 = 0L;

    public synchronized void set(long i) {
        v1 = i;
    }

    public synchronized long get() {
        return v1;
    }

    public long getAndIncrement() {
        long temp = get();
        temp += 1L;
        set(temp);
        return temp;
    }
}