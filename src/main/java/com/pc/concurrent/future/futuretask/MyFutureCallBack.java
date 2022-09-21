package com.pc.concurrent.future.futuretask;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

/**
 *
 *
 * 使用FutureTask实现回调机制
 *
 * @author dongxie
 * @date 11:18 2020-05-10
 */
public class MyFutureCallBack<T> extends FutureTask<T> {

    private CopyOnWriteArrayList<BiConsumer<T, ? super Exception>> listeners = new CopyOnWriteArrayList<>();

    public MyFutureCallBack(Callable<T> callable) {
        super(callable);
    }

    public MyFutureCallBack(Runnable runnable, T result) {
        super(runnable, result);
    }

    public void addListener(BiConsumer<T, ? super Exception> consumer) {
        if (isDone()) {
            publish(consumer);
            listeners.clear();
            return;
        }
        listeners.add(consumer);
    }

    private void publish(BiConsumer<T, ? super Exception> consumer) {
        Exception exception = null;
        T t = null;
        try {
            t = get();
        } catch (Exception e) {
            exception = e;
        }
        final Exception finalException = exception;
        final T finalT = t;
        consumer.accept(finalT, finalException);
    }

    /**
     * FutureTask的run()方法执行完会调用钩子方法done
     */
    @Override
    protected void done() {
        publish((e, t) -> listeners.forEach(l -> l.accept(e, t)));
        listeners.clear();
    }




    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyFutureCallBack<String> futureCallBack = new MyFutureCallBack<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5L);
                System.out.println("call---"+System.currentTimeMillis());
                return "result-"+new Random().nextInt(10);
            }
        });

        //可以注册多个监听,执行完成后会推送结果
        futureCallBack.addListener(new BiConsumer<String, Exception>() {
            @Override
            public void accept(String s, Exception e) {
                if (s != null) {
                    System.out.println("accept-"+System.currentTimeMillis()+"---"+s);
                } else {
                    e.printStackTrace();
                }

            }
        });


        new Thread(futureCallBack).start();

        //也可以通过get拉取结果
//        System.out.println("get----"+System.currentTimeMillis()+"---"+futureCallBack.get());
    }




}

