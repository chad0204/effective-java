package com.pc;

import com.pc.test.A;
import com.pc.test.Demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-05-07 10:12 上午
 */
public class Test {

    static class Demo {
        private List<String> res = new ArrayList<>();

        public List<String> getRes() {
            return res;
        }

        public void setRes(List<String> res) {
            this.res = res;
        }
    }


    public static void main(String[] args) {

        Demo demo = new Demo();

        if(demo==null || demo.getRes().size()==0) {
            System.out.println("1");
        }



    }



    public String testFinally() {
        String res = "aaa";

        try {
            System.out.println("try");
            return res;
        } finally {
            System.out.println("finally");
        }
    }
}
