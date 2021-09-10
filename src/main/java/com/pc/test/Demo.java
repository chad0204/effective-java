package com.pc.test;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author pengchao
 * @date 16:16 2020-08-19
 */
public class Demo {

    private static Set<Long> printLogTaskIds = Sets.newHashSet();

    static {
        printLogTaskIds.add(1L);
        printLogTaskIds.add(2L);


    }




    public static void info(Long checkTaskId) {
        if (!printLogTaskIds.contains(checkTaskId)) {
            return ;
        }

        System.out.println("打印:"+checkTaskId);
    }


    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {


        Demo.info(2L);






    }






    private int value;
    private String name;
    private Integer value1;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue1() {
        return value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }
}
