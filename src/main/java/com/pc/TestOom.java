package com.pc;


import com.pc.test.A;
import com.pc.test.Demo;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *
 * @author dongxie
 * @date 10:24 2019-12-24
 */
public class TestOom {





    public static void main(String[] args) {

//        List<VO> list = Arrays.asList(new VO("1","aa"),new VO("2","bb"),new VO("2","cc"));
//
////        Map<String, VO> dbCardIdMap = list.stream().collect(Collectors.toMap(VO::getId, op -> op));
//        Map<String, VO> dbCardIdMap = list.stream().collect(Collectors.toMap(VO::getName, op -> op));
//
//        System.out.println(dbCardIdMap);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService service =  Executors.newFixedThreadPool(2, new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread t = new Thread(r);
                t.setName("PC-Thread-" +atomicInteger.incrementAndGet());
                return t;
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                testOom();
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                testOom();
            }
        });


        byte[] bytes = {-44,-21};
        new String(bytes, StandardCharsets.UTF_8);


        System.out.println();


    }


    public static void testOom() {
        new OOM().oom();
    }



    public void deadCycle() {
        List<VO> list = new ArrayList<>();
        list.add(new VO("begin","name"));

        for (int i = 0; i < list.size(); i ++) {
            if (i == list.size() - 1) {
                list.add(new VO(""+i,""+i));
            }
        }
    }



}
