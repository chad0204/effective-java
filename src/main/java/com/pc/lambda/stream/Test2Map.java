package com.pc.lambda.stream;

import com.pc.lambda.transform.UserDto;
import com.pc.test.A;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author pengchao
 * @date 11:22 2020-08-17
 */
public class Test2Map {

    public static void main(String[] args) {

        List<UserDto> list = new ArrayList<>();
        list.add(new UserDto(101L,"aa"));
        list.add(new UserDto(102L,"bb"));
        list.add(new UserDto(103L,"cc"));
        list.add(new UserDto(104L,"aa"));
        list.add(new UserDto(104L,"dd"));
        list.add(new UserDto(null,"aa"));


        Map<Long, List<UserDto>> toListMap =
                list.stream().filter(x->x.getId()!=null).collect(Collectors.groupingBy(UserDto::getId));

        System.out.println(toListMap);



        Map<Long, UserDto> toMap = list.stream()
                .collect(Collectors.toMap(UserDto::getId, a -> a, (k1, k2) -> k1));


        System.out.println(toMap);


        Map<String,List<String>> map = new HashMap<>();

        map.put("aa", Arrays.asList("11","22"));
        map.put("bb", Arrays.asList("33","44"));
        map.put("cc", Arrays.asList("55","66"));

        List<String> list1 = map.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

        System.out.println();





        String str = "aaa";
        List list2 = new ArrayList();
        list2.add(str);


        String cmp = "aaa";
        System.out.println(list2.contains(cmp));



    }
}
