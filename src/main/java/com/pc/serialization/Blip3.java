package com.pc.serialization;

import java.io.*;

/**
 *
 * 实现Externalizable，没有任何域可以自动序列化，只能通过writeExternal和
 * readExternal方法来实现存储数据和恢复数据的功能
 *
 *
 * Externalizable让所有的域都不自动序列化
 * transient 修饰的域不会被序列化
 * 添加（注意不是覆盖）writeObject和readObject方法，这样当对象被序列化和反序列化时就不会使用默认的序列化方法，而是调用我们提供的。
 *
 *
 *
 * @author pengchao
 * @since 17:08 2019-09-09
 */
public class Blip3 implements Externalizable {

    private int i;
    private String s;
    public Blip3() {
        System.out.println("Blip3 Constructor");
    }

    public Blip3(String x, int a) {
        System.out.println("Blip3(String x,int a)");
        s = x;
        i = a;
    }

    @Override
    public String toString() {
        return s +" "+ i;
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        //Externalizable调用无参构造器，所以不在readExternal初始化，s和i将是空值
//        s = (String) in.readObject();
//        i = in.readInt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects:");
        Blip3 blip3 = new Blip3("A String", 47);

        System.out.println(blip3);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        System.out.println("Saving objects");
        out.writeObject(blip3);
        out.flush();

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        System.out.println("Recovering b3:");
        blip3 = (Blip3) in.readObject();
        System.out.println(blip3);
    }
}
