package com.pc.sort;


/**
 * 归并排序
 *
 * @author dongxie
 * @date 22:09 2020-04-28
 */
public class MergeSort {

    private int[] permutation;
    private int[] reversePermutation;
    private static final int INSERTIONSORT_THRESHOLD = 7;

    private void mergeSort(int[] src,
                           int[] dest,
                           int low,
                           int high,
                           int off) {

        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i=low; i<high; i++)
                for (int j=i; j>low &&
                        ((Comparable) dest[j-1]).compareTo(dest[j])>0; j--)
                    swap(dest, j, j-1);
            return;
        }

        // Recursively sort halves of dest into src
        int destLow  = low;
        int destHigh = high;
        low  += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off);
        mergeSort(dest, src, mid, high, -off);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (((Comparable)src[mid-1]).compareTo(src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && ((Comparable)src[p]).compareTo(src[q])<=0) {
                dest[i] = src[p];
                permutation[reversePermutation[p++]] = i;
            } else {
                dest[i] = src[q];
                permutation[reversePermutation[q++]] = i;
            }
        }

        for (int i = destLow; i < destHigh; ++i) {
            reversePermutation[permutation[i]] = i;
        }
    }

    private void swap(int[] x, int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
        permutation[reversePermutation[a]] = b;
        permutation[reversePermutation[b]] = a;
        int tp = reversePermutation[a];
        reversePermutation[a] = reversePermutation[b];
        reversePermutation[b] = tp;
    }



}
