package com.pc.jvm.classload.classload;

import com.pc.jvm.model.Demo1;
import com.pc.jvm.model.Demo2;

/**
 * 测试双亲委派
 *
 * @author dongxie
 * @date 10:54 2020-04-28
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = Test.class.getClassLoader();//用rt.jar下的类Class获取就是null
        Class<?> c1 = classLoader.loadClass("com.pc.model.Demo1");//双亲委派
        Demo1 demo1 = (Demo1) c1.newInstance();
        demo1.test();

        Class<?> c2 = Class.forName("com.pc.model.Demo2");
        Demo2 demo2 = (Demo2) c2.newInstance();
        demo2.test();


        new Thread();


    }




    class BoostrapClassLoader {


    }


    class ExtClassLoader {

        private BoostrapClassLoader parent;

        public void loadClass() throws ClassNotFoundException {
            throw new ClassNotFoundException("ClassName");
        }
    }


    class AppClassLoader {

        private ExtClassLoader parent;

        public void loadClass() throws ClassNotFoundException {
            throw new ClassNotFoundException("ClassName");
        }
    }


}
