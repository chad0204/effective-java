package com.pc.jvm.classload.classload;

import com.pc.jvm.classload.Test;
import java.io.IOException;
import java.io.InputStream;

/**
 * 构建一个类加载器，它可以加载与自己在同一路径下的Class文件
 *
 * 测试不同的类加载器，加载出来的类是互相独立的。用equal(),isAssignableFrom(),isInstance()和instanceof判断都是false。
 *
 * 返回false这是因为Java虚拟机中同时存在了两个ClassLoaderTest类，一个是由虚拟
 * 机的应用程序类加载器所加载的，另外一个是由我们自定义的类加载器加载的，虽然它
 * 们都来自同一个Class文件，但在Java虚拟机中仍然是两个互相独立的类，做对象所属
 * 类型检查时的结果自然为 false。
 *
 * @author dongxie
 * @date 13:43 2020-04-28
 */
public class ClassLoadTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //自定义ClassLoader
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1)+".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name); }
                    byte[] b = new byte[is.available()]; is.read(b);
                    return defineClass(name, b, 0, b.length);

                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        //自定义类加载器加载
        Object myObj = myLoader.loadClass("com.pc.classload.ClassLoadTest").newInstance();

        //AppClassLoad加载
        ClassLoader classLoader = Test.class.getClassLoader();
        Object jvmObj = classLoader.loadClass("com.pc.classload.ClassLoadTest").newInstance();

        //对象确实是ClassLoadTest实例
        System.out.println(myObj.getClass());
        System.out.println(jvmObj.getClass());
        //比较对象
        System.out.println(myObj.equals(jvmObj));//false
        System.out.println(myObj.getClass().isInstance(jvmObj));//false
        System.out.println(myObj instanceof ClassLoadTest);//false
        System.out.println(jvmObj instanceof ClassLoadTest);//true
    }
}
