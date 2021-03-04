package com.pc.algorithm.other;

import java.util.Stack;

/**
 * 20. 有效的括号
 *
 * @author pengchao
 * @date 16:33 2021-03-02
 */
public class IsValid {

    public static void main(String[] args) {


        System.out.println(isValid("][[([]){}]]"));

    }

    public static boolean isValid(String s) {
        Stack<Character> left = new Stack<>();

        for(Character c : s.toCharArray()) {
            if(c.equals('(') || c.equals('[') || c.equals('{')) {
                left.push(c);
            } else {

                if(!left.empty() && leftOf(c).equals(left.peek())) {
                    left.pop();
                } else {
                    return false;
                }
            }


        }


        return left.empty();

    }

    //
    public static Character leftOf(Character c) {
        if(c.equals('}')) {
            return '{';
        } else if(c.equals(']')) {
            return '[';
        } else if(c.equals(')')) {
            return '(';
        }
        throw new IllegalArgumentException();
    }
}
