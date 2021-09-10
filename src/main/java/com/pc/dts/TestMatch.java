package com.pc.dts;

import com.google.common.base.Function;
import com.pc.VO;
import com.pc.test.Demo;
import com.sun.tools.javac.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-07-05 6:09 下午
 */
public class TestMatch {




    public static void main(String[] args) {

        Long a = null;
        String.valueOf(a);

        Pair<String,String> pair = Pair.of("aaa","bbb");

        System.out.println();


        List<VO> list = Arrays.asList(new VO("1","aa"),new VO("2","bb"),new VO("3","cc"));


        long start = System.currentTimeMillis();
//        list = list.stream().filter(x -> x.getId().equals("22")).collect(Collectors.toList());
//        list = list.stream().parallel().filter(x -> x.getId().equals("22")).collect(Collectors.toList());
//        list = list.stream().sequential().filter(x -> x.getId().equals("22")).collect(Collectors.toList());

        list = list.stream()/*.sequential()*/.peek(x -> x.setName(x.getName()+"-1")).collect(Collectors.toList());
        List<String> names = list.stream()/*.sequential()*/.map(VO::getName).collect(Collectors.toList());

        System.out.println("rt: " + (System.currentTimeMillis() - start));
        
        
        Map<String,String> source = new HashMap<>();
        source.put("aa","11");
        source.put("bb","22");
        source.put("cc","33");
        source.put("dd","44");


        Map<String,String> target1 = new HashMap<>();
        target1.put("aa","11");
        target1.put("bb","22");
        target1.put("cc","55");
        target1.put("dd","66");


        Map<String,String> target2 = new HashMap<>();
        target2.put("aa","11");
        target2.put("bb","22");
        target2.put("cc","33");
        target2.put("dd","44");

        /*
         * anyMatch表示，判断的条件里，任意一个元素成功，返回true
         *
         * allMatch表示，判断条件里的元素，所有的都是，返回true
         *
         * noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true
         */
        boolean res = source.keySet().stream().allMatch(s -> {
            return !source.get(s).equals(target1.get(s));
        });


        boolean res1 = source.keySet().stream().allMatch(s -> {
            return !source.get(s).equals(target2.get(s));
        });




        System.out.println();

    }


}
