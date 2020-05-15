package com.pc.util;

import java.util.Arrays;
import java.util.List;

/**
 * 给invoker组装filter链
 *
 * @author dongxie
 * @date 15:59 2020-05-15
 */
public class ChainTest {

    public static void main(String[] args) {

        Invoker invoker = buildInvokerChain();


        invoker.invoke("");

    }


    public static Invoker buildInvokerChain() {
        Invoker last = new DubboInvoker();

        List<Filter> filters = Arrays.asList(new OneFilter(),new TwoFilter(),new ThreeFilter(),new FourFilter());

        for(int i = filters.size()-1;i>=0;i--) {
            final Filter filter = filters.get(i);
            final Invoker next = last;
            last = new Invoker() {
                @Override
                public String invoke(String param) {
                    return filter.invoke(next,"");
                }
            };
        }
        return last;
    }
}
