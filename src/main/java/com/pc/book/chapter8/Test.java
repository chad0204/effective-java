package com.pc.book.chapter8;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * 可变类的不安全性
 *
 * @author dongxie
 * @date 14:12 2020-01-09
 */
public class Test {

    public static void main(String[] args) {
        Map<Demo,String> map = new HashMap<>();

        Demo demo1 = new Demo("aa");
        Demo demo2 = new Demo("bb");

        map.put(demo1,"1");
        map.put(demo2,"2");

        System.out.println(map);


        demo2.setName("aa");

        System.out.println(map);//出现了相同key的元素
    }

}

class Demo {

    private String name;

    Demo(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Demo demo = (Demo) o;

        return Objects.equals(name, demo.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                '}';
    }
}
