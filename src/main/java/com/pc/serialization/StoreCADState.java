package com.pc.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author pengchao
 * @since 19:19 2019-09-09
 */
abstract class Shape implements Serializable {
    public static final int RED = 1, BLUE =2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;

    public abstract void setColor(int newColor);
    public abstract int getColor();

    public Shape(int xPos, int yPos, int dimension) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return getClass()+"{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", dimension=" + dimension +
                ", color=" + getColor() +
                '}'+'\n';
    }

    public static Shape randomFactory() {
        int xVal = rand.nextInt(100);
        int yVal = rand.nextInt(100);
        int dim = rand.nextInt(100);
        switch (counter++ % 3) {
            default:
            case 0: return new Circle(xVal,yVal,dim);
            case 1: return new Square(xVal,yVal,dim);
            case 2: return new Line(xVal,yVal,dim);
        }

    }
}

class Circle extends Shape  {
    private static int color = RED;//直接赋值

    public Circle(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}

class Square extends Shape {
    private static int color;//构造器赋值

    public Square(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
        color = RED;
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}

class Line extends Shape {
    private static int color = RED;

    public static void serializeStaticState(ObjectOutputStream os) throws IOException {
        os.writeInt(color);
    }

    public static void deserializeStaticState(ObjectInputStream os) throws IOException {
        color = os.readInt();
    }

    public Line(int xPos, int yPos, int dimension) {
        super(xPos, yPos, dimension);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}


public class StoreCADState {

    /**
     *
     * 静态成员变量不能被序列化
     * @author pengchao
     * @since 20:13 2019-09-09
     * @see CADTest#main(String[])
     *
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Class<? extends Shape>> shapeTypes = new ArrayList<>();
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);

        List<Shape> shapes = new ArrayList<>();
        //随机制造一些图形
        for(int i=0; i<10; i++) {
            shapes.add(Shape.randomFactory());
        }
        //设置图形为GREEN
        for(int i=0; i<10; i++) {
            shapes.get(i).setColor(Shape.GREEN);
        }

        //写文件
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CADstate.out"));
        out.writeObject(shapeTypes);
        Line.serializeStaticState(out);
        out.writeObject(shapes);
        System.out.println(shapes);
        System.out.println("====================");

        //读取，能读到是因为在同一个jvm下
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADstate.out"));
//        List<Class<? extends Shape>> shapeTypesRead = (List<Class<? extends Shape>>) in.readObject();
//        Line.deserializeStaticState(in);
//        List<Shape> shapesRead = (List<Shape>) in.readObject();
//        System.out.println(shapesRead);

    }

}
