package com.pc.inherit.field;

/**
 * 继承，用父类接子类，会丢失子类特有的域吗？
 *
 * 会有，但是无法获取
 *
 * @author pengchao
 * @date 16:06 2020-08-31
 */
public class Test {


    public static void main(String[] args) {

        Test test = new Test();


        Car car = test.getCar();


        System.out.println(car.toString());







    }


    public Car getCar() {
        BMWCar bmwCar = new BMWCar();
        bmwCar.setWheel("4");
        bmwCar.setWing("2");
        return bmwCar;
    }


}


class Car {

    private String wheel;

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }
}

class BMWCar extends Car {

    private String wing;

    public String getWing() {
        return wing;
    }

    public void setWing(String wing) {
        this.wing = wing;
    }
}
