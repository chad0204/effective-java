package com.pc.lambda.stream;

import com.pc.lambda.transform.UserDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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


    }
}
