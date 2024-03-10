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
        diffClassLoaderType();
    }


    public static void diffClassLoaderType() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //自定义ClassLoader
        ClassLoader customLoader = new CustomClassLoader();
        //自定义类加载器加载
        Object myObj = customLoader.loadClass("com.pc.jvm.classload.classload.ClassLoadTest").newInstance();

        //AppClassLoad加载
        ClassLoader classLoader = Test.class.getClassLoader();
        Object jvmObj = classLoader.loadClass("com.pc.jvm.classload.classload.ClassLoadTest").newInstance();

        //对象确实是ClassLoadTest实例
        System.out.println(myObj.getClass());
        System.out.println(jvmObj.getClass());
        //比较对象
        System.out.println(myObj.equals(jvmObj));//false
        System.out.println(myObj.getClass().isInstance(jvmObj));//false
        System.out.println(myObj instanceof ClassLoadTest);//false
        System.out.println(jvmObj instanceof ClassLoadTest);//true
        ((ClassLoadTest)jvmObj).printClassLoader();//正常
    }

    //同一个类加载器类型的不同实例加载的类也是不同的
    public void sameClassLoaderType() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //自定义ClassLoader
        ClassLoader customLoader1 = new CustomClassLoader();
        //自定义类加载器加载
        Object obj1 = customLoader1.loadClass("com.pc.jvm.classload.classload.ClassLoadTest").newInstance();

        //同一个类加载器类型的不同实例加载的类也是不同的
        ClassLoader customLoader2 = new CustomClassLoader();
        Object obj2 = customLoader2.loadClass("com.pc.jvm.classload.classload.ClassLoadTest").newInstance();
        System.out.println(obj1.equals(obj2));//false
        System.out.println(obj1.getClass().isInstance(obj2));//false
        System.out.println(obj1 instanceof ClassLoadTest);//false 因为ClassLoadTest是jvm加载的
        System.out.println(obj2 instanceof ClassLoadTest);//false
        ((ClassLoadTest)obj2).printClassLoader();//会报错
    }

    //同一个类加载器类型的不同实例加载的类也是不同的, 反射加载
    public void sameClassLoaderTypeV2() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //自定义ClassLoader
        ClassLoader customLoader1 = new CustomClassLoader();
        //自定义ClassLoader
        ClassLoader customLoader2 = new CustomClassLoader();

        //同一个类加载器类型的不同实例加载的类也是不同的
        Object obj1 = Class.forName("com.pc.jvm.classload.classload.ClassLoadTest", true, customLoader1).newInstance();
        Object obj2 = Class.forName("com.pc.jvm.classload.classload.ClassLoadTest", true, customLoader2).newInstance();

        //对象确实是ClassLoadTest实例
        System.out.println(obj1.getClass());
        System.out.println(obj2.getClass());
        //比较对象
        System.out.println(obj1.equals(obj2));//false
        System.out.println(obj1.getClass().isInstance(obj2));//false
        System.out.println(obj1 instanceof ClassLoadTest);//false 因为ClassLoadTest是jvm加载的
        System.out.println(obj2 instanceof ClassLoadTest);//false
        ((ClassLoadTest)obj2).printClassLoader();//会报错
    }

    public void printClassLoader() {
        System.out.println(this.getClass().getClassLoader());
    }

}

class CustomClassLoader extends ClassLoader {
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
}
