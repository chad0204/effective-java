package com.pc.inherit;

import com.pc.inherit.param.ReqImpl;
import com.pc.inherit.param.RespImpl;

/**
 * TODO
 *
 * @author pengchao
 * @date 10:46 2020-08-03
 */
public class SonA extends Father<ReqImpl,RespImpl> {

    @Override
    public String say() {
        return "我是你儿子";
    }

    public void homework() {
        System.out.println("写作业");
    }


    @Override
    public String testF2S() {
        return "son";
    }
}
