package com.pc.book.chapter6.enum_;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  分析java.lang.Enum类
 *
 *  实现了Comparable<E>, Serializable接口
 *
 * values()是所有枚举的父类Enum在编译期生成的
 *
 * valueOf()是java.lang.Enum的方法
 *
 * ordinal() 返回枚举元素的排序位置
 *
 * @author dongxie
 * @date 15:00 2020-01-07
 */
public class TestOnePiece {

    private static final Map<String,OnePiece> stringEnumMap =
            Stream.of(OnePiece.values()).collect(Collectors.toMap(Object::toString,e->e));

    public static void main(String[] args) throws NoSuchMethodException {

        //values方法
        for(OnePiece onePiece: OnePiece.values()) {
            System.out.println(onePiece);//toString
            System.out.println(onePiece.ordinal());//ordinal方法
            f(onePiece);
        }


        //valueOf方法
        OnePiece onePiece = OnePiece.valueOf("LUFEI");



        Class<?> clazz = OnePiece.class;

        clazz.getMethods();
        clazz.getMethod("values");


    }

    private static void f(OnePiece onePiece) {
        switch (onePiece) {
            case LUFEI:
                System.out.println(0);
                break;
            case NAMEI:
                System.out.println(1);
                break;
            case SUOLONG:
                System.out.println(2);
                break;
            default:
                System.out.println(3);
        }
    }
}
