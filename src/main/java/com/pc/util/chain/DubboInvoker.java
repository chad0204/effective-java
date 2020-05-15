package com.pc.util.chain;

/**
 * TODO
 *
 * @author dongxie
 * @date 15:58 2020-05-15
 */
public class DubboInvoker implements Invoker {
    @Override
    public String invoke(String param) {
        System.out.println("dubbo");
        return "dubbo";
    }
}
