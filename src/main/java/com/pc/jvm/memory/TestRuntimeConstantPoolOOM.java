package com.pc.jvm.memory;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试运行时常量池 OOM
 *
 *  jdk7使用下面的
 *  -XX:PermSize=6M -XX:MaxPermSize=6M
 *  或者jdk8使用
 *  -XX:MaxMeta-spaceSize
 *  都不会出现异常
 *
 *  是因为自JDK 7起，原本存放在永久代的字符串常量池被移至Java堆之中，所以在JDK7及以上版本，
 *  限制方法区的容量对该测试用例来说是毫无意义的。这时候使用-Xmx参数限制最大堆到6MB就能够
 *  看到以下两种运行结果之一
 *
 * @author dongxie
 * @date 10:57 2020-04-27
 */
public class TestRuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern()); }
    }
}
