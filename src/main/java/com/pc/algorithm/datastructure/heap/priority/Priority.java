package com.pc.algorithm.datastructure.heap.priority;

import java.util.PriorityQueue;

/**
 * 优先队列
 *
 * @author pengchao
 * @date 14:13 2021-02-23
 */
public class Priority {


    public static void main(String[] args) {


        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();


        priorityQueue.add(3);
        priorityQueue.add(4);
        priorityQueue.add(7);
        priorityQueue.add(8);
        priorityQueue.add(1);
        priorityQueue.add(2);
        priorityQueue.add(6);


        int size = priorityQueue.size();
        for(int i=0;i<size;i++) {

            System.out.println(priorityQueue.poll());

        }






    }
}
