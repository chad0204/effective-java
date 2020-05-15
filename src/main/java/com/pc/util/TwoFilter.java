package com.pc.util;

/**
 * TODO
 *
 * @author dongxie
 * @date 15:57 2020-05-15
 */
public class TwoFilter implements Filter {
    @Override
    public String invoke(Invoker invoker,String param) {
        System.out.println("two");
        return invoker.invoke(param);
    }
}
