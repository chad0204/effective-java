package com.pc.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author pengchao
 * @date 16:16 2020-08-19
 */
public class Demo {


    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {


        System.out.println(1);


        executor.submit(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println(1/0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(2);

        //invoke com.dfire.pay.center.service.payment.IPaymentService.payRefundQuery('99228109',['992281098El3yzZmQWrbMJgihIAAIs'])


        Demo demo = new Demo();
        demo.setName("aa");
        demo.setValue(2);

        List<Demo> list = Arrays.asList(demo,new Demo(),new Demo());



        List<Demo> list1 = list.stream().filter(x->"aa".equals(x.getName())).collect(Collectors.toList());

        System.out.println(list1.get(0).getName());


        int a = 1;
        int ratio = 96;

        double radio = ratio / 10.0;



        if(a==1) {
            radio = (100-ratio) / 10.0;
        }

        System.out.println(radio);



    }






    private int value;
    private String name;
    private Integer value1;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue1() {
        return value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }
}
