package com.pc.lianlian.mevl.demo.queryclass;

import com.pc.lianlian.mevl.demo.FactorResult;

import java.util.List;
import java.util.Map;

/**
 *
 * @author pengchao
 * @since 2022/8/19 14:40
 */
public abstract class QueryService<T> {

    public FactorResult<T> query(Map<String, Object> paramValues,
                                 List<String> inputParamNames,
                                 String dataType) {

        //TODO 校验入参类型
        for(String param : inputParamNames) {
            if (!paramValues.containsKey(param) || paramValues.get(param) != null) {
                throw new IllegalArgumentException();
            }
        }

        T data = doQuery(paramValues);

        //TODO 校验返回值类型
        if (!data.getClass().getName().equals(dataType)) {
            throw new IllegalArgumentException();
        }

        return new FactorResult<>(data, "SUCCESS");
    }

    public abstract T doQuery(Map<String, Object> params);
}
