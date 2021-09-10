package com.pc.guava;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.pc.VO;
import com.pc.test.A;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-07-21 11:07 上午
 */
public class TestCollection {

    public static void main(String[] args) {
        testMap();


    }


    public static void testMap() {

        Multimap<Integer, Integer> map = HashMultimap.create();

        map.put(1, 2);
        map.put(1, 2);//去重
        map.put(1, 3);
        map.put(1, 4);
        map.put(2, 3);
        map.put(3, 3);
        map.put(4, 3);
        map.put(5, 3);
        System.out.println(map);


        Multiset<Integer> set = map.keys();
        System.out.println(set);

        Set<Integer> kset = map.keySet();

        Collection<Integer> res =  map.get(1);




        BO bo = new BO("schema1",1);
        BO bo1 = new BO("schema1",2);
        BO bo2 = new BO("schema1",3);
        BO bo3 = new BO("schema2",4);
        BO bo4 = new BO("schema3",5);


        List<BO> list = new ArrayList<>();
        list.add(bo);
        list.add(bo1);
        list.add(bo2);
        list.add(bo3);
        list.add(bo4);

        HashMultimap<String, BO> map2 = HashMultimap.create();
        list.forEach(b -> {
            map2.put(b.getName(),b);
        });



        new ArrayList<>(map2.values());


        map2.entries().forEach(stringBOEntry -> {

            stringBOEntry.getKey();
            stringBOEntry.getValue();


        });



        System.out.println();



    }






}


class BO {

    private String name;
    private Integer value;

    public BO(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public BO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BO bo = (BO) o;
        return Objects.equals(name, bo.name) && Objects.equals(value, bo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
