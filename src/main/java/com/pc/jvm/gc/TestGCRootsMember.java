package com.pc.jvm.gc;

/**
 * 测试成员变量引用对象是否可作为GCRoots
 * -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 *
 * @author ljl
 */
public class TestGCRootsMember {
    private static int _10MB = 10 * 1024 * 1024;
    private TestGCRootsMember t;
    private byte[] memory;

    public TestGCRootsMember(int size) {
        memory = new byte[size];
    }

    public static void main(String[] args) {
        TestGCRootsMember t4 = new TestGCRootsMember(4 * _10MB);
        t4.t = new TestGCRootsMember(8 * _10MB);
        t4 = null;
        System.gc();
    }

    /**
     *  成员变量
     *  GC后，年轻代回收146473K->592K，复制了到老年代146473K->600K。成员变量全部回收，没有复制
     * [GC (System.gc()) [PSYoungGen: 146473K->592K(458752K)] 146473K->600K(983040K), 0.0033336 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
     * fullGC后，年轻代回收592K->0K，老年代回收8K->490K。全部回收
     * [Full GC (System.gc()) [PSYoungGen: 592K->0K(458752K)] [ParOldGen: 8K->490K(524288K)] 600K->490K(983040K), [Metaspace: 3087K->3087K(1056768K)], 0.0069857 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
     *
     * 静态变量
     * GC后，年轻代回收146473K->624K，复制了到老年代146473K->82552K。静态变量复制到老年代
     * [GC (System.gc()) [PSYoungGen: 146473K->624K(458752K)] 146473K->82552K(983040K), 0.0577847 secs] [Times: user=0.23 sys=0.02, real=0.06 secs]
     * fullGC后，年轻代回收624K->0K，老年代回收81928K->82410K。没有回收静态变量
     * [Full GC (System.gc()) [PSYoungGen: 624K->0K(458752K)] [ParOldGen: 81928K->82410K(524288K)] 82552K->82410K(983040K), [Metaspace: 3088K->3088K(1056768K)], 0.0242931 secs] [Times: user=0.06 sys=0.00, real=0.02 secs]
     *
     */
}
