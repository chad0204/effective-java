package com.pc.util.chain.filter;

import com.pc.util.chain.Invoker;

/**
 * TODO
 *
 * @author dongxie
 * @date 15:57 2020-05-15
 */
public class ThreeFilter implements Filter {
    @Override
    public String invoke(Invoker invoker, String param) {
        System.out.println("three");
        return invoker.invoke(param);
    }
}
