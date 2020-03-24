package com.pc;

import com.pc.concurrent.synchronized_.App;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author dongxie
 * @date 10:24 2019-12-24
 */
public class Test {

    public static void main(String[] args) {
        int a = 0;
        int b = 0;
        boolean flag = a==b && ((a=a+1)==b);
        System.out.println(flag);
        System.out.println(a);

        App app = new App();

    }
}
