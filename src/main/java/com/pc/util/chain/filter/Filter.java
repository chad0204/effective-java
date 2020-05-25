package com.pc.util.chain.filter;

import com.pc.util.chain.Invoker;

/**
 * TODO
 *
 * @author dongxie
 * @date 15:54 2020-05-15
 */
public interface Filter {

    String invoke(Invoker invoker, String param);
}
