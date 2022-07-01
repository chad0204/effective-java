package com.pc.concurrent.future.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class MergeSort extends RecursiveTask<Integer> {

    private int threshold = 1;

    public MergeSort(int threshold) {
        this.threshold = threshold;
    }

    @Override
    protected Integer compute() {
        if (threshold > 1) {

        } else {

        }
        return 0;
    }
}
