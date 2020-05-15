package com.pc.util.chain;

/**
 * TODO
 *
 * @author dongxie
 * @date 15:54 2020-05-15
 */
public interface Filter {

    String invoke(Invoker invoker,String param);
}
