package com.pc.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-07-26 12:11 下午
 */
public class BatchQuery {

    public static void main(String[] args) {
        batchQuery();
    }

    public static void batchQuery () {

        int count = 1001;

        int totalPageCount = count % getPageSize() == 0 ? count / getPageSize() : count / getPageSize() + 1;
        int pageNum =0;
        for (int i = 1; i <= totalPageCount; i++) {


            System.out.println(pageNum + " -- " +getPageSize());


            pageNum = getPageSize()+pageNum;
        }

        System.out.println(null instanceof String);


    }

    private static int getPageSize() {
        return 10;
    }
}
