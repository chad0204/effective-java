package com.pc.concurrent.future.forkjoin;

import com.google.common.collect.Lists;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class MergeSortTask extends RecursiveAction {

    private int threshold = 1;
    private int[] arr;
    private int start;
    private int end;

    public MergeSortTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        int mid = (start + end) / 2;
        if (end - start >= threshold) {
            //分而治之
            MergeSortTask sortLeft = new MergeSortTask(arr, start, mid);
            MergeSortTask sortRight = new MergeSortTask(arr, mid + 1, end);
            ForkJoinTask.invokeAll(Lists.newArrayList(sortLeft, sortRight));
            //TODO 这里需不需要join?
//            sortLeft.join();
//            sortRight.join();

            merge(arr, start, mid, end);
        } else {
            //处理
            merge(arr, start, mid, end);
        }
    }

    public void mergesort(int[] arr, int start, int end) {
        if (start <= end) {
            return;
        }

        int mid = (end + start)/2;
        mergesort(arr, start, mid);
        mergesort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    private void merge(int[] arr, int start, int mid,int end) {

        int[] temp = new int[end - start + 1];
        int p = 0;

        int left = start;
        int right = mid + 1;


        while (left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                temp[p] = arr[left];
                left++;
            } else {
                temp[p] = arr[right];
                right++;
            }
            p++;
        }

        while (left <= mid) {
            temp[p] = arr[left];
            p++;
            left++;
        }

        while (right <= end) {
            temp[p] = arr[right];
            p++;
            right++;
        }

        System.arraycopy(temp, 0, arr, start, temp.length);
    }

    public static void main(String[] args) {
        int[] a = {4,2,1,4,7,5,3,8,2,7,1,78,89,6,5,4,8,5};

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.invoke(new MergeSortTask(a, 0, a.length - 1));

        System.out.println(a[0]);

    }





}



