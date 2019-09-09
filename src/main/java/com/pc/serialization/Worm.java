package com.pc.serialization;

import java.io.*;
import java.util.Random;

/**
 *
 * 两种序列化方式
 * @author pengchao
 * @since 15:37 2019-09-09
 */

class Data implements Serializable {
    private int n;
    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
}

public class Worm implements Serializable {
    private static Random random = new Random(47);
    private Data[] d = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))};
    private Worm next;
    private char c;

    //value of i == number of segments
    public Worm(int i, char x) {
        System.out.println("Worm constructor: "+i);
        c = x;
        if(--i > 0) {
            next = new Worm(i, (char) (x+1));
        }
    }

    public Worm() {
        System.out.println("Default constructor");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for(Data data : d) {
            result.append(data);
        }
        result.append(")");
        if(next!=null)
            result.append(next);
        return result.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Worm w = new Worm(6,'a');
        System.out.println("w = "+w);


        //1.文件方式
        FileOutputStream fout = new FileOutputStream("worm.out");
        ObjectOutputStream out1 = new ObjectOutputStream(fout);
        out1.writeObject("Worm storage\n");
        out1.writeObject(w);
        out1.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
        String s = (String) in.readObject();
        Worm w2 = (Worm) in.readObject();
        System.out.println(s+"w2 = "+w2);


        //2.数组方式
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bout);
        out2.writeObject("Worm storage\n");
        out2.writeObject(w);
        out2.flush();

        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
        s = (String) in2.readObject();
        Worm w3 = (Worm) in2.readObject();
        System.out.println(s+"w3 = "+w3);




    }
}




