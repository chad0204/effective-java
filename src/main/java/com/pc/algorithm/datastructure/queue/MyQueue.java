package com.pc.algorithm.datastructure.queue;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 * @author pengchao
 * @date 15:38 2021-01-21
 */
public class MyQueue {

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);



    }


    Stack<Integer> in;
    Stack<Integer> out;

    int size = 0;

    /** Initialize your data structure here. */
    public MyQueue() {
        in = new Stack<>();
        out = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        in.push(x);
        size++;
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(out.empty()) {
            //in -> out
            while (!in.empty()) {
                int num = in.pop();
                out.push(num);
            }
        }
        int num = out.pop();
        size--;
        return num;
    }

    /** Get the front element. */
    public int peek() {
        if(out.empty()) {
            //in -> out
            while (!in.empty()) {
                int num = in.pop();
                out.push(num);
            }
        }
        return out.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return size == 0;
    }
}
