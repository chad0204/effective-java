package com.pc;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author dongxie
 * @date 10:24 2019-12-24
 */
public class Test {

    public static void main(String[] args) {

        List<VO> list = Arrays.asList(new VO("1","aa"),new VO("2","bb"),new VO("2","cc"));

//        Map<String, VO> dbCardIdMap = list.stream().collect(Collectors.toMap(VO::getId, op -> op));
        Map<String, VO> dbCardIdMap = list.stream().collect(Collectors.toMap(VO::getName, op -> op));

        System.out.println(dbCardIdMap);


    }



}
