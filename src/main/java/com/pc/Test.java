package com.pc;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


        Map<String, String> map = new HashMap<>();
        map.put("aa", "bb");

        String s = JSON.toJSONString(map);
        Map map1 = JSON.parseObject(s, Map.class);

        System.out.println(JSON.toJSONString(map));

        System.out.println();



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
