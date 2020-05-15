package com.pc.util.chain;

/**
 * TODO
 *
 * @author dongxie
 * @date 15:57 2020-05-15
 */
public class FourFilter implements Filter {
    @Override
    public String invoke(Invoker invoker,String param) {
        System.out.println("four");
        return invoker.invoke(param);
    }
}
