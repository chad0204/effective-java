package com.pc.algorithm.datastructure.stack;

import java.util.LinkedList;

/**
 * 用队列实现栈
 *
 * @author pengchao
 * @date 15:57 2021-01-21
 */
public class MyStack {


    public static void main(String[] args) {


        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }



    }

    LinkedList<Integer> queue;


    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.push(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.pollFirst();
    }

    /** Get the top element. */
    public int top() {
        return queue.peekFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
