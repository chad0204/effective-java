package com.pc.inherit;

import com.pc.inherit.param.IReq;
import com.pc.inherit.param.IResp;
import java.lang.reflect.ParameterizedType;

/**
 * TODO
 *
 * @author pengchao
 * @date 10:45 2020-08-03
 */
public class Father<REQ extends IReq,RESP extends IResp> extends GrandFather<REQ,RESP> {

    @Override
    public String say() {
        return "叫爸爸";
    }


    /**
     * 获取范型参数
     * @return
     */
    protected REQ buildTcRequest() {
        //当前是子类调用，所以拿到的是子类范型的类
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<REQ> req = (Class<REQ>) parameterizedType.getActualTypeArguments()[0];
        Class<REQ> resp = (Class<REQ>) parameterizedType.getActualTypeArguments()[1];
        REQ tcRequest;
        try {
            tcRequest = req.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return tcRequest;
    }


    public String testF2S() {
        return null;
    }

}
