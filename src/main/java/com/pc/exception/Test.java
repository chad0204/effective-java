package com.pc.exception;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-04-27 11:16 上午
 */
public class Test {


    private Map<String, Integer> scriptCache = Maps.newConcurrentMap();


    public static void main(String[] args) {
        Test t = new Test();

        Integer res;
        try {
            res = t.parse(null);
        } catch (Exception e) {
            res = 0;
        }


        if(res==0) {
            System.out.println("retry");
        }
    }



    public int parse(String content) {
        try {
            Integer script = scriptCache.get(content);
            if (script == null) {
                scriptCache.put(content, 1);
            }
            if (null != script) {

                return 1;
            }
        } catch (Exception e) {
//            throw new IllegalArgumentException(e);
        }
        return 0;
    }
}
