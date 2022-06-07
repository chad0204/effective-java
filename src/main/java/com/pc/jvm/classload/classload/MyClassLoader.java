package com.pc.jvm.classload.classload;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * TODO
 *
 * @author pengchao
 * @date 22:36 2022/5/27
 */
public class MyClassLoader {


    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        if (!Thread.currentThread().getName().equals("start")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Method main = null;
                    try {
                        String p = "/Users/pengchao/develop/IdeaProjects/workspace/effective-java/target/effective-java-1.0-SNAPSHOT.jar";
                        File file = new File(p);
                        URLClassLoader myClassLoader = new URLClassLoader(new URL[] {file.toURI().toURL()}, null);

                        Class<?> aClass = myClassLoader.loadClass("com.pc.jvm.classload.MyClassLoader");
                        Thread.currentThread().setContextClassLoader(aClass.getClassLoader());//重点
                        main = aClass.getDeclaredMethod("main", String[].class);
                        main.invoke(null, new Object[]{args});
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            },"start").start();
        } else {
            Class.forName("com.mysql.jdbc.Driver");//当前是urlClassLoader，无法加载这个类，且urlClassLoader无父类
            getConnection();
        }


//        getConnection();

    }



    public static Connection getConnection() {
        try {
            //如果直接通过反射调用，那么就没有调用者，也就没有调用者类加载器，只有boostrap加载器，加载不到。而且不是递归过来的，也没法用AppClassLoader。
//            Method m1 = DriverManager.class.getDeclaredMethod("getConnection",String.class, java.util.Properties.class, Class.class);
//            m1.setAccessible(true);
//            m1.invoke(null, url, new Properties(), null);
//
            Method m2 = DriverManager.class.getDeclaredMethod("getConnection",String.class, Properties.class);
            m2.invoke(null, url, new Properties());


//            return DriverManager.getConnection(url, username, password);
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String url = "jdbc:mysql://localhost:3306/pengchao?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String username = "root";
    private static final String password = "123456";
}
