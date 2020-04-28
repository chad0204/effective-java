package com.pc.jvm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 测试OutOfMemoryError
 *
 * maven install 时需要在pom中设置主类
 *
 *  java -Xmx1m -Xms1m -jar xx.jar
 *
 * @author dongxie
 * @date 17:29 2020-04-26
 */
public class TestHeapOOM {

    public static void main(String[] args) throws InterruptedException {

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(new byte[1024*1024]);
        }

        TimeUnit.SECONDS.sleep(1000);

    }
}
