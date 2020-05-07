package com.pc.datastructure;

import java.util.HashMap;

/**
 * 测试类
 *
 * @author dongxie
 * @date 14:41 2020-04-30
 */
public class Main {


    public static void main(String[] args) {
        BinarySearchTree<Integer,String> bst = new BinarySearchTree<>();

        bst.put(4,"四");
        bst.put(2,"二");
        bst.put(6,"六");
        bst.put(7,"七");
        bst.put(1,"一");
        bst.put(3,"三");
        bst.put(5,"五");

        bst.put(10,"十");
        bst.put(11,"十一");
        bst.put(9,"九");

        System.out.println(bst.floor(8));

        HashMap<Integer,String> map = new HashMap<>();
        map.put(4,"四");
        map.put(2,"二");
        map.put(6,"六");
        map.put(7,"七");
        map.put(1,"一");
        map.put(3,"三");
        map.put(5,"五");


        System.out.println(map);




    }


}
