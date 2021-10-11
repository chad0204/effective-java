package com.pc.groovy.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.pc.groovy.GroovyCheckRuleEngine;

import java.util.Map;

/**
 * @author pengchao
 * @description: 离线任务
 * @date 2021-10-08 11:44 上午
 */
public class TestOffline {

    public static void main(String[] args) {

        String data = "{\"dataAttributes\":{\"special_item_id\":\"42791260_0\",\"kdt_id\":\"42791260\",\"status\":\"1\"},\"uniqueKey\":\"42791260_0\"}";

//        Map<String, Object> originData = Maps.newHashMap();
//        originData.put("uniqueKey", checkTaskSourceData.getUniqueKey());
//        originData.put("dataAttributes", JSON.parseObject(checkTaskSourceData.getDataMapping(), CheckTaskSourceDataMappingBO.class).getDataAttributes());


        Map originData = JSON.parseObject(data, Map.class);


        String content = "return dataAttributes.kdt_id";


        GroovyCheckRuleEngine engine = new GroovyCheckRuleEngine();
        Object res = engine.executeRule(content,originData,"TEST");
        System.out.println(res);
    }
}


class CheckTaskSourceDataMappingBO {

    private String uniqueKey;

    private Map<String, String> dataAttributes = Maps.newHashMap();

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Map<String, String> getDataAttributes() {
        return dataAttributes;
    }

    public void setDataAttributes(Map<String, String> dataAttributes) {
        this.dataAttributes = dataAttributes;
    }
}
