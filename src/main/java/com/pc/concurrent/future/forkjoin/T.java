package com.pc.concurrent.future.forkjoin;

import java.util.concurrent.*;

public class T {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        ForkJoinTask forkJoinTask = new ForkJoinTask() {
            @Override
            public Object getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(Object value) {

            }

            @Override
            protected boolean exec() {
                return false;
            }
        };

        ForkJoinWorkerThread forkJoinWorkerThread;


        RecursiveTask recursiveTask;
        RecursiveAction recursiveAction;


    }


}
