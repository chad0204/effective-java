package com.pc.concurrent.aqs;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 存放产品的容器
 */
public class ProductContainer {

    private static LinkedList<String> CONTAINER = new LinkedList<>();

    private static int MAX_SIZE = 10;

    private static Random random = new Random(47);

    private Lock lock = new ReentrantLock();
    private Condition producerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    private static volatile ProductContainer container;

    private ProductContainer() {}

    static ProductContainer getContainer() {
        if(container==null) {
            synchronized (ProductContainer.class) {
                if(container==null) {
                    return container = new ProductContainer();
                }
            }
        }
        return container;
    }

    public String putProduct() throws InterruptedException {//存
        lock.lock();
        try {
            while (CONTAINER.size()>= MAX_SIZE) {
                producerCondition.await();
            }
            String pro = "产品"+random.nextInt(1000);
            CONTAINER.add(pro);
            consumerCondition.signal();//一定要在lock块中
            return pro;
        } finally {
            lock.unlock();
        }
    }

    public String getProduct() throws InterruptedException {//取
        lock.lock();
        try {
            while (CONTAINER.size()<=0) {
                consumerCondition.await();
            }
            String product = CONTAINER.removeFirst();
            producerCondition.signalAll();
            return product;
        } finally {
            lock.unlock();
        }
    }

    public synchronized Integer getSize() {
        return CONTAINER.size();
    }


    private static ExecutorService service = new ThreadPoolExecutor(10,10,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        for(int i=0;i<5;i++) {
            service.execute(new Consumer());
        }
        for(int i=0;i<5;i++) {
            service.execute(new Producer());
        }
    }

}


class Producer implements Runnable {

    private final ProductContainer container = ProductContainer.getContainer();

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("生产一个产品："+container.putProduct()+",size="+container.getSize());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {

    private final ProductContainer container = ProductContainer.getContainer();

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("消费一个产品："+container.getProduct()+",size="+container.getSize());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
