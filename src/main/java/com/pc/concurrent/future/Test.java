package com.pc.concurrent.future;

import com.pc.concurrent.future.futuretask.MyFutureTask;

import java.util.concurrent.*;

/**
 *
 *
 * @author dongxie
 * @date 18:21 2020-02-07
 */
public class Test {

    private static final ExecutorService service = new ThreadPoolExecutor(2,2,0L,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(10));

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//        testSingleTask();
        testMoreTask();
    }



    private static void testSingleTask() throws InterruptedException {
        try {
            Future<Integer> future = new Test().calculate(10);

            //没有结束，每三秒打印一次消息，直到结束
//        while (!future.isDone()) {
//            System.out.println("now is calculating...");
//            TimeUnit.SECONDS.sleep(3);
//        }

            //一直阻塞，直到获取到结果
        System.out.println("calculated result:"+future.get());

            //阻塞到指定时间内没有返回则会报TimeoutException
//        System.out.println(future.get(5,TimeUnit.SECONDS));


            //计算过程中，取消任务线程,boolean值表示线程是否应该被中断
//        future.cancel(false);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    private static void testMoreTask() throws ExecutionException, InterruptedException {
//        PFutureTask<String> future = new PFutureTask<>(new Runnable() {//Runnable还是适配器RunnableAdapter包装成Callable，call方法调用run方法
//
//            @Override
//            public void run() {
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"run");
//
//        new Thread(future).start();
//
//
//
//
//        System.out.println(System.currentTimeMillis()+":"+future.isDone());
//        System.out.println("ok:"+future.get());
//        System.out.println(System.currentTimeMillis()+":"+future.isDone());


        MyFutureTask<String> future1 = new MyFutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "call";
            }
        });

        new Thread(future1).start();

        System.out.println(System.currentTimeMillis()+":"+future1.isDone());
        System.out.println("ok:"+future1.get());
        System.out.println(System.currentTimeMillis()+":"+future1.isDone());




    }

    //向线程池中加入一个Callable任务,使用submit返回一个Future
    private Future<Integer> calculate(Integer num) {

        return service.submit(() -> {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("begin");
            return num * num;
        });
    }
}
