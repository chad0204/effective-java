package com.pc.something;

import java.math.BigDecimal;

/**
 * @author pengchao
 * @since 09:09 2019-09-09
 *
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(1.03-0.42);
        System.out.println(1.00-9*0.10);
        BigDecimal bigDecimal1 = new BigDecimal("1.03");
        BigDecimal bigDecimal2 = new BigDecimal("0.42");
        BigDecimal bigDecimal3 = new BigDecimal("1.00");
        BigDecimal bigDecimal4 = new BigDecimal("0.10");
        System.out.println(bigDecimal1.subtract(bigDecimal2));
        System.out.println(bigDecimal3.subtract(bigDecimal4));








    }
}
