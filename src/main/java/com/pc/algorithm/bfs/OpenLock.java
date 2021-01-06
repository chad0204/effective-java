package com.pc.algorithm.bfs;

import java.util.*;

/**
 * 752. 打开转盘锁
 *
 *      从初始节点（0000）开始，每个节点只转动一次，有8种可能，也就可延伸8个分支，完成一层。每个子分支又有8个分支，都扩展一次，又遍历一层。
 *
 *      扩展过程会出现重复节点，需要剔除
 *
 *      如果某个分支遇到 dead，那么这个分支就不在扩展了，但是别的分支还继续。
 *
 *      如果某个分支遇到target，则完成，层数就是最小转动次数。
 *
 *
 * @author pengchao
 * @date 11:12 2021-01-06
 */
public class OpenLock {


    public static void main(String[] args) {


        System.out.println(openLock(new String[]{"0900"},"0002"));



    }

    /**
     * 开锁
     * 752
     * @param deadends
     * @param target
     * @return
     */
    public static int openLock(String[] deadends, String target) {

        Set<String> visited = new HashSet<>();
        Set<String> deadSet = new HashSet<>();
        Collections.addAll(deadSet, deadends);

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");//addLast
        visited.add("0000");

        int step = 0;
        while (!queue.isEmpty()) {
            //这个循环是循环上一层的元素
            for(int i= queue.size();i>0;i--) {
                String curr = queue.poll();//removeFirst


                if(deadSet.contains(curr)) {
                    continue;//表示这个分支走不通了，下一层不会有这个可能的延伸
                }

                if(curr.equals(target)) {
                    return step;
                }

                //这个循环是找下一层的所有节点
                //不像数只有两个节点，图有多个节点
                //这里每个数都可以向上或向下转，总共4个数
                for(int j=0;j<4;j++) {
                    //转动会有重复值，剔除
                    String up  = plusOne(curr,j);
                    if(!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down  = minusOne(curr,j);
                    if(!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            //增加层数
            step++;
        }

        return -1;
    }

    //向下转动
    private static String minusOne(String curr, int j) {
        char[] chars = curr.toCharArray();

        if(chars[j]=='9') {
            chars[j] = '0';
        } else {
            chars[j] = (char) (chars[j] + 1);
        }

        return new String(chars);
    }

    //向上转动
    private static String plusOne(String curr, int j) {
        char[] chars = curr.toCharArray();
        if(chars[j]=='0') {
            chars[j] = '9';
        } else {
            chars[j] = (char) (chars[j] - 1);
        }
        return new String(chars);
    }

}
