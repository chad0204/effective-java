package com.pc.concurrent.future.callback;

import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.FutureTask;
import java.util.function.BiConsumer;

/**
 *
 *
 *
 * @author dongxie
 * @date 11:18 2020-05-10
 */
public class FutureCallBack<T> extends FutureTask<T> {

    private CopyOnWriteArrayList<BiConsumer<T, ? super Exception>> listeners = new CopyOnWriteArrayList<>();

    public FutureCallBack(Callable<T> callable) {
        super(callable);
    }

    public FutureCallBack(Runnable runnable, T result) {
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




}

