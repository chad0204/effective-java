package com.pc.concurrent.future.dubbo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author dongxie
 * @date 23:39 2020-05-09
 */
public class DefaultFuture implements ResponseFuture,Runnable {

    private static final Map<Long, DefaultFuture> FUTURES = new ConcurrentHashMap<Long, DefaultFuture>();

    private final long id;

    private final Request request;

    private volatile Response response;

    private volatile ResponseCallback callback;

    private long getId() {
        return id;
    }


    private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();


    static {
        Thread th = new Thread(new RemotingInvocationTimeoutScan(), "DubboResponseTimeoutScanTimer");
        th.setDaemon(true);
        th.start();
    }

    @Override
    public void setCallback(ResponseCallback callback) {
        if (isDone()) {
            invokeCallback(callback);
        } else {
            boolean isdone = false;
            lock.lock();
            try {
                if (!isDone()) {
                    this.callback = callback;
                } else {
                    isdone = true;
                }
            } finally {
                lock.unlock();
            }
            if (isdone) {
                invokeCallback(callback);
            }
        }
    }


    public DefaultFuture(Request request) {
        this.request = request;
        // 获取请求 id，这个 id 很重要，后面还会见到
        /*
         * 一般情况下，服务消费方会并发调用多个服务，每个用户线程发送请求后，会调用不同 DefaultFuture 对象的 get 方法进行等待。
         * 一段时间后，服务消费方的线程池会收到多个响应对象。这个时候要考虑一个问题，如何将每个响应对象传递给相应的 DefaultFuture
         * 对象，且不出错。答案是通过调用编号。DefaultFuture 被创建时，会要求传入一个 Request 对象。此时 DefaultFuture 可从
         * Request 对象中获取调用编号，并将 <调用编号, DefaultFuture 对象> 映射关系存入到静态 Map 中，即 FUTURES。线程池中的
         * 线程在收到 Response 对象后，会根据 Response 对象中的调用编号到 FUTURES 集合中取出相应的 DefaultFuture 对象，然后再将
         * Response 对象设置到 DefaultFuture 对象中。最后再唤醒用户线程，这样用户线程即可从 DefaultFuture 对象中获取调用结果了。
         */
        this.id = request.getId();
        // 存储 <requestId, DefaultFuture> 映射关系到 FUTURES 中
        FUTURES.put(id, this);
    }


    @Override
    public Object get(int timeout) {
        if (timeout <= 0) {
            timeout = 5000;
        }

        // 检测服务提供方是否成功返回了调用结果
        if (!isDone()) {
            long start = System.currentTimeMillis();
            lock.lock();
            try {
                // 循环检测服务提供方是否成功返回了调用结果
                while (!isDone()) {
                    // 如果调用结果尚未返回，这里等待一段时间
                    done.await(timeout, TimeUnit.MILLISECONDS);
                    // 如果调用结果成功返回，或等待超时，此时跳出 while 循环，执行后续的逻辑
                    if (isDone() || System.currentTimeMillis() - start > timeout) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            // 如果调用结果仍未返回，则抛出超时异常
            if (!isDone()) {
                try {
                    throw new TimeoutException("");
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }
        // 返回调用结果
        return returnFromResponse();
    }

    private Object returnFromResponse() {
        Response res = response;
        if (res == null) {
            throw new IllegalStateException("response cannot be null");
        }
        // 如果调用结果的状态为 Response.OK，则表示调用过程正常，服务提供方成功返回了调用结果
        if (res.getStatus() == Response.OK) {
            return res.getResult();//返回结果
        }

        throw new IllegalArgumentException();
    }

    @Override
    public boolean isDone() {
        return response != null;
    }


    //扫描所有future，清除超时的future
    private static class RemotingInvocationTimeoutScan implements Runnable {

        @Override
        public void run() {
//            while (true) {
//                try {
//                    for (DefaultFuture future : FUTURES.values()) {
//                        if (future == null || future.isDone()) {
//                            continue;
//                        }
//
//                        Response timeoutResponse = new Response(future.getId());
//                        // set timeout status.
////                        timeoutResponse.setStatus(future.isSent() ? Response.SERVER_TIMEOUT : Response.CLIENT_TIMEOUT);
////                        timeoutResponse.setErrorMessage(future.getTimeoutMessage(true));
//                        // handle response.
//                        DefaultFuture.received(timeoutResponse);
//                    }
//                    Thread.sleep(30);
//                } catch (Throwable e) {
////                    logger.error("Exception when scan the timeout invocation of remoting.", e);
//                }
//            }
        }
    }


    public static void received(Response response) {
        // 根据调用编号从 FUTURES 集合中查找指定的 DefaultFuture 对象
        DefaultFuture future = FUTURES.remove(response.getId());
        if (future != null) {
            // 继续向下调用
            future.doReceived(response);
        } else {
            System.out.println("future is null");
        }
    }

    private void doReceived(Response res) {
        lock.lock();
        try {
            // 保存响应对象
            response = res;
            if (done != null) {
                // 唤醒用户线程，用户线程在get()方法中阻塞
                done.signal();
            }
        } finally {
            lock.unlock();
        }
        if (callback != null) {
            done();
        }
    }


    public void done() {
        invokeCallback(callback);
    }


    private void invokeCallback(ResponseCallback c) {
        ResponseCallback callbackCopy = c;
        if (callbackCopy == null) {
            throw new NullPointerException("callback cannot be null.");
        }
        c = null;
        Response res = response;
        if (res == null) {
            throw new IllegalStateException("response cannot be null. url:");
        }

        // 如果调用结果的状态为 Response.OK，则表示调用过程正常，服务提供方成功返回了调用结果
        if (res.getStatus() == Response.OK) {
            try {
                callbackCopy.done(res.getResult());
            } catch (Exception e) {
                System.out.println("callback invoke error .reasult:" + res.getResult()+e);
            }
        }
        // 抛出异常
        else if (res.getStatus() == Response.CLIENT_TIMEOUT || res.getStatus() == Response.SERVER_TIMEOUT) {
            try {
                TimeoutException te = new TimeoutException(res.getErrorMessage());
                callbackCopy.caught(te);
            } catch (Exception e) {
                System.out.println("callback invoke error "+e);
            }
        } else {
            try {
                RuntimeException re = new RuntimeException(res.getErrorMessage());
                callbackCopy.caught(re);
            } catch (Exception e) {
                System.out.println("callback invoke error "+e);
            }
        }
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Response response = new Response();
        response.setId(request.getId());
        response.setResult("result");
        response.setStatus(Response.OK);
        DefaultFuture.received(response);
    }




    public static DefaultFuture getFuture(long id) {
        return FUTURES.get(id);
    }
}
