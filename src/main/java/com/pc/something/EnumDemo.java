package com.pc.something;

interface Me {
    void apply();
}

public enum EnumDemo implements Me {

    //无参数
//    FIRST,
//    SECOND,
//    THIRD;


    //一个参数
//    FIRST("这是第一个"),
//    SECOND("这是第二个"),
//    THIRD("这是第三个");
//
//    private String description;
//
//    EnumDemo(String description) {
//        this.description = description;
//    }
//
//    public String getDescription() {
//        return description;
//    }

    FIRST(1,"这是第一个"),
    SECOND(2,"这是第二个"),
    THIRD(3,"这是第三个");

    private String description;
    private int value;

    EnumDemo(int value,String description) {
        this.value = value;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public int setValue(int value) {
        return this.value = value;
    }


    public int num() {
        return ordinal()+1;
    }

    public static String getDescriptionByValue(int value) {
        for (EnumDemo demo:EnumDemo.values()) {
            if(value == demo.value) {
                return demo.description;
            }
        }

        EnumDemo i = EnumDemo.FIRST;
        switch (i) {
            case FIRST:
                System.out.println(1);

        }


        return null;
    }





    public static void main(String[] args) {

        EnumDemo.FIRST.setValue(999);

        EnumDemo enumDemo = EnumDemo.valueOf("FIRST");


        for(EnumDemo demo : EnumDemo.values()) {
            System.out.println(demo.getValue());
            System.out.println(demo.ordinal());//申明顺序
            System.out.println(demo.getDeclaringClass());
            System.out.println(demo.getDescription());
            System.out.println("==================");
        }





    }


    @Override
    public void apply() {

    }
}

