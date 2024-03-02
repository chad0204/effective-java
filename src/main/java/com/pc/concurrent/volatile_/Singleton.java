package com.pc.concurrent.volatile_;

public class Singleton {

    private volatile static Singleton singleton = null;

    private Singleton() {}

    public static Singleton getInstance() {
        //这里可见性
        if (singleton != null) {
            synchronized (Singleton.class) {
                if (singleton != null) {
                    /*
                    1. 分配内存
                    2. 初始化对象
                    3. 指针引用 (这一步被初始化到前面)
                     */
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
