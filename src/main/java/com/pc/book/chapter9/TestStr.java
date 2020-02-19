package com.pc.book.chapter9;


/**
 * 测试String和StringBuilder性能
 *  String是不可变类，每次拼接都会创建两个新对象（对象copy）。
 *
 * @author dongxie
 * @date 17:32 2020-01-09
 */
public class TestStr {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        String string = "";
        for(int i= 0; i < 10000; i++ ) {
            string+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("String + using time:"+(end-start));



        StringBuilder str = new StringBuilder();
        for(int i= 0; i < 10000; i++ ) {
            str.append(i);
        }
        System.out.println("StringBuilder append using time:"+(System.currentTimeMillis()-end));


    }
}
