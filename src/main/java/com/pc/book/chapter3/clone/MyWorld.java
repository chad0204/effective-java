package com.pc.book.chapter3.clone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *  实现深拷贝
 * @author pengchao
 * @since 17:45 2019-09-09
 */
class House implements Serializable {}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;
    Animal(String name, House h) {
        this.name = name;
        preferredHouse = h;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", preferredHouse=" + preferredHouse +
                '}';
    }
}


public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Bosco the dog",house));
        animals.add(new Animal("Ralph the hamster",house));
        animals.add(new Animal("Molly the cat",house));

        System.out.println("animals: "+animals);

        System.out.println("=================================");

        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream out1 = new ObjectOutputStream(buf1);
        out1.writeObject(animals);
        out1.writeObject(animals);//写两次
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));


        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(buf2);
        out2.writeObject(animals);//写一次
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));

        List animals1 = (List) in1.readObject();
        List animals2 = (List) in1.readObject();//写两次才可以读两次

        List animals3 = (List) in2.readObject();

        //三个对象都进行了深拷贝
        System.out.println("animals1:"+animals1);
        System.out.println("animals2:"+animals2);
        System.out.println("animals3:"+animals3);

    }

}
