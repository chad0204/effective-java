package com.pc.something;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengchao
 * @since 15:59 2019-09-06
 */
public class ObservableSet<E> extends ForwardingSet<E> {
    public ObservableSet(Set<E> s) {
        super(s);
    }

    private final List<SetObserver<E>> observers = new ArrayList<>();//存放观察者

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        //注释部分是会死锁的部分
//        synchronized (observers) {
//            for(SetObserver<E> observer: observers ) {
//                observer.added(this,element);
//            }
//        }

        //下面用快照方式解决
        List<SetObserver<E>> snapshot = null;
        synchronized (observers) {
            snapshot = new ArrayList<>(observers);
        }
        for(SetObserver<E> observer: snapshot ) {
            observer.added(this,element);
        }

    }

    @Override
    public boolean add(E e) {
        boolean added =  super.add(e);
        if(added) {
            notifyElementAdded(e);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for(E element : c) {
            result |= add(element);//都成功才算成功
        }
        return result;
    }

    public static void main(String[] args) {
        ObservableSet<Integer> observableSet = new ObservableSet<>(new HashSet<>());

        //1. 正常，观察者回调打印1-100
//        observableSet.addObserver((set, element) -> System.out.println(element));

        //2.ConcurrentModificationException，同时发生遍历所有观察者和删除观察者
//        observableSet.addObserver(new SetObserver<Integer>() {
//            @Override
//            public void added(ObservableSet<Integer> set, Integer element) {
//                System.out.println(element);
//                if(element==23) {
//                    observableSet.removeObserver(this);//加了this就不能用lambda表达式了
//                }
//            }
//        });

        //3. 死锁 ，主线程添加元素后的回调方法占用锁并等待后台线程删除观察者，后台线程移除观察者的方法因为主线程已经抢占锁还无法执行。
        observableSet.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if(element==23) {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    try {
                        executorService.submit(() -> set.removeObserver(this)).get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new AssertionError();
                    } finally {
                        executorService.shutdown();
                    }
                }
            }
        });

        for(int i = 0; i < 100; i++) {
            observableSet.add(i);
        }

    }
}
