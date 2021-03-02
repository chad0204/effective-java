package com.pc.jvm.gc;

/**
 * 测试方法区中的静态变量引用的对象作为GCRoots
 * -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 *
 * 扩展：方法区存与堆一样,是各个线程共享的内存区域,用于存放已被虚拟机加载的类信息,常量,静态变量,即时编译器编译后的代码等数据。
 * @author ljl
 * */
public class TestGCRootsStatic {
    private static int _10MB = 10 * 1024 * 1024;
    private byte[] memory;

    private static TestGCRootsStatic t;

    public TestGCRootsStatic(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        TestGCRootsStatic t2 = new TestGCRootsStatic(4 * _10MB);
        t2.t = new TestGCRootsStatic(8 * _10MB);
        t2 = null;
        System.gc();
        //t2设置为null后，还剩下静态变量t没有被回收
    }

}
