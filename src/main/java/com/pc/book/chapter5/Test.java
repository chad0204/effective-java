package com.pc.book.chapter5;

import java.util.ArrayList;
import java.util.List;

/**
 * 范型关系
 *
 * @author dongxie
 * @date 17:04 2020-01-06
 */
public class Test {

    public static void main(String[] args) {

        Sub sub = new Sub();
        System.out.println(sub instanceof Super);
//        System.out.println(new ArrayList<Sub>() instanceof ArrayList<Super>);//不能比较
        System.out.println(new Sub[10] instanceof Super[]);//true

    }
}

class Super {

}

class Sub extends Super {

}
