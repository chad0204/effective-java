package com.pc.util;

/**
 * TODO
 *
 * @author dongxie
 * @date 15:56 2020-05-15
 */
public class OneFilter implements Filter {
    @Override
    public String invoke(Invoker invoker,String param) {
        System.out.println("one");
        return invoker.invoke(param);
    }
}
