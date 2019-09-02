package com.pc.reflect;

class Building {}
class House extends Building {}

/**
 * 使用cast
 */
public class ClassCasts {

    public static void main(String[] args) {
        Building building = new House();

        Class<House> houseClass = House.class;


//        House house1 = (House) building;
        House house = houseClass.cast(building);



    }
}
