package com.pc.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * TODO
 *
 * @author pengchao
 * @since 20:12 2019-09-09
 */
public class CADTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //读取
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADstate.out"));
        List<Class<? extends Shape>> shapeTypesRead = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapesRead = (List<Shape>) in.readObject();
        System.out.println(shapesRead);
    }
}
