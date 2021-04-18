package com.pc.concurrent.threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 用法
 *
 *      Thread类自己维护了一个ThreadLocalMap变量
 *          ThreadLocal.ThreadLocalMap threadLocals = null;
 *      该变量在ThreadLocal#createMap(Thread t, T firstValue)方法中初始化
 *
 *      ThreadLocal只是一个工具类，提供了创建ThreadLocalMap，设置ThreadLocalMap，获取ThreadLocalMap和删除ThreadLocalMap的方法
 *      ThreadLocalMap是Thread的一个变量，用来维护一个线程独享的变量
 *
 *      ThreadLocalMap使用线性探索法解决hash冲突
 *
 *
 *      内存泄漏，如果线程是线程池中管理的不消亡的线程，由于ThreadLocalMap的entry是弱引用，在gc发生后，会回收，导致出现null.value，此时value将无法回收。所以需要调用remove删除。
 *      （当threadLocal 不为静态变量，且被回收的时候才会导致weakRef为null）
 *      ThreadLocal被回收了，value没有回收，出现null-value键值对
 *
 *
 *      什么是弱引用
 *      A a = new A();
 *      B b = new B(a);
 *      a = null;
 *      上面的代码，a=null，对象A依然不会被GC,因为被b强引用了。
 *      A a = new A();
 *      WeakReference b = new WeakReference(a);
 *      a = null;
 *      此时，a=null之后，a就会被回收
 *
 *      Entry(ThreadLocal<?> k, Object v) {
 *          super(k);//k为弱引用
 *          value = v;//value为强引用
 *      }
 *
 *      当线程不会消亡，
 *      Entry中如果ThreadLocal没有其他强引用对象引用或者不是静态变量，由于ThreadLocal在Entry中被弱引用，ThreadLocal对象会被回收，而value被Entry强引用，导致出现null.value，此时value将无法被获取。
 *      为了解决这个问题，
 *              一是ThreadLocal自身的get方法会清除key为null的Entry（expungeStaleEntry()方法。但是如果ThreadLocal是static或者就没有调用set,get,remove，那么还是不会回收
 *              一是需要手动调用remove()，就像加锁解锁一样。
 *
 *
 *
 *     ThreadLocal使用弱引用，是因为ThreadLocal就是一个工具类，使用完就可以回收了，而value可以在使用过程中主动清除。
 *
 *
 *
 * @author pengchao
 * @date 11:36 2020-06-24
 */
public class MyThreadLocal {

    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>(){
        /**
         * 用户可以自定义initialValue()初始化方法，来初始化threadLocal的值。
         *
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected Object initialValue()
        {
            System.out.println(Thread.currentThread().getName()+" 调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
            return null;
        }
    };

    private static final ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();





    public static void main(String[] args) {

//        new Thread(new IntegerTask(),"Thread-Integer").start();
//        new Thread(new StringTask(),"Thread-String").start();


        new Thread(() -> {

            //一个Thread只有一个ThreadLocalMap,存放(threadLocal,value)键值,一个ThreadLocal实例set多次则会覆盖,key就是threadLocal

            //如果Thread的threadLocalMap已经初始化，那么另一个ThreadLocal实例则不会创建ThreadLocalMap,而是将(threadLocal2,value)键值加入到原先的map中

            //每个线程都只有一个threadLocalMap,一个线程用一个threadLocal对象只能操作tlm新增一个k-v元素；使用多个threadLocal才能给tlm新增多个元素
            threadLocal.set(1);
            threadLocal.set(2);
            threadLocal2.set(1);
            threadLocal2.set(2);

            System.out.println((Integer) threadLocal.get());
            System.out.println((Integer) threadLocal2.get());

            threadLocal.remove();


        },"Thread-more").start();


    }


    private static class IntegerTask implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                //通过get方法获取线程变量
                if(null == threadLocal.get()) {
                    threadLocal.set(0);
                    System.out.println(Thread.currentThread().getName()+" set("+0+")");
                } else {
                    int num = (Integer)threadLocal.get();
                    threadLocal.set(num + 1);
                    System.out.println(Thread.currentThread().getName()+" set("+(num + 1)+")");
                    if(i == 3) {
                        threadLocal.remove();
                    }

                }

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class StringTask implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                //通过get方法获取线程变量
                if(null == threadLocal.get()) {
                    threadLocal.set("a");
                    System.out.println(Thread.currentThread().getName()+" set(a)");
                } else {
                    String value = (String) threadLocal.get();
                    threadLocal.set("a"+value);
                    System.out.println(Thread.currentThread().getName()+" set(a"+value+")");
                    if(i == 3) {
                        threadLocal.remove();
                    }
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
