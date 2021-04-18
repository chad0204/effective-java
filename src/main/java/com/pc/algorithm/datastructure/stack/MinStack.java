package com.pc.algorithm.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 155. 最小栈
 *
 * @author pengchao
 * @date 17:24 2021-03-30
 */
public class MinStack {


    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> min_stack = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int val) {
        stack.push(val);
        //这里没有比当前min_stack更小的元素，压栈还是当前最小的元素
        if(min_stack.size()==0) {
            min_stack.push(val);
        } else {
            min_stack.push(Math.min(min_stack.peek(),val));
        }
    }

    public void pop() {
        stack.pop();
        min_stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min_stack.peek();
    }

}
