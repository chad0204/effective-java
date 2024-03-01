package com.pc.algorithm.datastructure.heap.priority;

import java.util.PriorityQueue;

public class KthLargest {

    private PriorityQueue<Integer> pq;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }


    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(3, nums);



    }

}