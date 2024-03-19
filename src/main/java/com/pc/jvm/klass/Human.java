package com.pc.jvm.klass;

public class Human {

    private static final String staticFinalValue = "staticFinalValue123";
    private static String staticValue = "staticValue123";
    private final String finalValue = "finalValue123";

    private String name;


    public void sayHello() {
        System.out.println("hello gay!");
    }


    public void say(Object human) {
        //如果调用human的方法, 还是会根据实际类型调用
        System.out.println("hello object!");
    }

    public void say(Human human) {
        //如果调用human的方法, 还是会根据实际类型调用
        System.out.println("hello human!");
    }

    public String say(String human) {
        //如果调用human的方法, 还是会根据实际类型调用
        System.out.println("hello human!");
        return null;
    }


    public void hardChoice(Long val) {
        System.out.println("Human hardChoice long");
    }

    public void hardChoice(Double val) {
        System.out.println("Human hardChoice double");
    }

    static {
        System.out.println(staticFinalValue);
    }


    public static void main(String[] args) {
        Human human = new Human();
        human.dispatch();
    }

    public void dynamicDispatch() {
        Human man = new Man();
        Human woman = new Woman();
        //重写方法是根据实际类型分派
        man.sayHello();
        woman.sayHello();
    }

    public void staticDispatch() {
        Human man = new Man();
        Human woman = new Woman();
        //三个重载方法都是根据外观类型分派
        Human human = new Human();
        human.say(human);
        human.say(man);
        human.say(woman);
    }

    public void dispatch() {
        Human human = new Human();
        Human man = new Man();
        human.hardChoice(1L);//invokevirtual #21 <Human.hardChoice : (Ljava/lang/Long;)V>
        man.hardChoice(1d);//invokevirtual #23 <Human.hardChoice : (Ljava/lang/Double;)V>
        // 0 new #11 <com/pc/jvm/klass/Human>
        // 3 dup
        // 4 invokespecial #12 <com/pc/jvm/klass/Human.<init> : ()V>
        // 7 astore_1
        // 8 new #14 <com/pc/jvm/klass/Man>
        //11 dup
        //12 invokespecial #15 <com/pc/jvm/klass/Man.<init> : ()V>
        //15 astore_2
        //16 aload_1
        //17 lconst_1
        //18 invokestatic #20 <java/lang/Long.valueOf : (J)Ljava/lang/Long;>
        //21 invokevirtual #21 <com/pc/jvm/klass/Human.hardChoice : (Ljava/lang/Long;)V>
        //24 aload_2
        //25 dconst_1
        //26 invokestatic #22 <java/lang/Double.valueOf : (D)Ljava/lang/Double;>
        //29 invokevirtual #23 <com/pc/jvm/klass/Human.hardChoice : (Ljava/lang/Double;)V>
        //32 return
    }

}


class Man extends Human {
    public void sayHello() {
        System.out.println("hello gentleman!");
    }

    public void say(Man man) {
        System.out.println("hello man!");
    }

    public void hardChoice(Long val) {
        System.out.println("Man hardChoice long");
    }

    public void hardChoice(Double val) {
        System.out.println("Man hardChoice double");
    }
}


class Woman extends Human {
    public void sayHello() {
        System.out.println("hello lady!");
    }

    public void say(Woman woman) {
        System.out.println("hello woman!");
    }
}
