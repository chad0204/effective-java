package com.pc.serialization;

import java.io.*;

/**
 * 实现Externalizable的类必须要有public 的构造器
 * 和Serializable不同，Serializable使用二进制来构造，不调用构造器。
 *
 * @author pengchao
 * @since 16:30 2019-09-09
 */
class Blip1 implements Externalizable {

    public Blip1() {
        System.out.println("Blip1 constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }
}

class Blip2 implements Externalizable {

    Blip2() {
        System.out.println("Blip2 constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }
}

public class Blips {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects:");
        Blip1 blip1 = new Blip1();
        Blip2 blip2 = new Blip2();

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        ObjectOutputStream out = new ObjectOutputStream(bout);
        System.out.println("Saving objects");
        out.writeObject(blip1);
        out.writeObject(blip2);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        System.out.println("Recovering b1:");
        blip1 = (Blip1) in.readObject();

//        System.out.println("Recovering b2:");
//        blip2 = (Blip2) in.readObject();//构造器私有不能读取



    }
}
