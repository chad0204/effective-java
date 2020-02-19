package com.pc.book.chapter6.enum_;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 测试EnumMap
 *      用EnumMap代替序数索引
 *
 * @author dongxie
 * @date 11:17 2020-01-08
 */
public class TestEnumMap {

    public static void main(String[] args) {

        /**
         * 1.使用数组和枚举索引，将对象分组
         * 数组和枚举相当于一种映射关系
         * 每个数组元素是一个set集合，包含该数组对应的枚举的若干对象。
         */

        //set数组
        Set[] plantsByLifeCycle = new Set[Plant.LifeCycle.values().length];

        //按照枚举元素分组
        for(int i = 0; i<plantsByLifeCycle.length; i++) {
            plantsByLifeCycle[i] = new HashSet<>();
        }

        List<Plant> garden = Arrays.asList(
                new Plant("一年生a",Plant.LifeCycle.ANNUAL),
                new Plant("多年生a",Plant.LifeCycle.PERENNIAL),
                new Plant("两年生a",Plant.LifeCycle.BIENNIAL),
                new Plant("一年生b",Plant.LifeCycle.ANNUAL),
                new Plant("多年生b",Plant.LifeCycle.PERENNIAL),
                new Plant("多年生c",Plant.LifeCycle.PERENNIAL),
                new Plant("两年生b",Plant.LifeCycle.BIENNIAL));

        for(Plant p : garden) {
            //按照枚举序号找到对应数组
            plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
        }

        for(int i = 0; i<plantsByLifeCycle.length; i++) {
            System.out.println(Plant.LifeCycle.values()[i]+" "+plantsByLifeCycle[i]);
        }

        System.out.println("====================================================================");

        /**
         * 2.使用EnumMap
         */
        Map<Plant.LifeCycle,Set<Plant>> plantsByLifeCycleMap = new EnumMap<>(Plant.LifeCycle.class);
        for(Plant.LifeCycle pl : Plant.LifeCycle.values()) {
            plantsByLifeCycleMap.put(pl,new HashSet<>());
        }
        for(Plant p : garden) {
            //按照枚举序号找到对应数组
            plantsByLifeCycleMap.get(p.lifeCycle).add(p);
        }

        System.out.println(plantsByLifeCycleMap);


        //lambda优化
        Map<Plant.LifeCycle,Set<Plant>> plantsByLifeCycleMap_ = garden.stream()
                .collect(Collectors.groupingBy(plant -> plant.lifeCycle,()->new EnumMap<>(Plant.LifeCycle.class),Collectors.toSet()));

        System.out.println(plantsByLifeCycleMap_);

    }

}

class Plant {
    enum LifeCycle {
        ANNUAL,//一年生
        PERENNIAL,//多年生
        BIENNIAL//两年生
    }

    final String name;
    final LifeCycle lifeCycle;

    public Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }
}
