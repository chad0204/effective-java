package com.pc.groovy.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.pc.groovy.GroovyCheckRuleEngine;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author pengchao
 * @description: 时间戳转日期
 * @date 2021-09-09 12:06 下午
 */
public class TestDate {

    public static void main(String[] args) {

        GroovyCheckRuleEngine engine = new GroovyCheckRuleEngine();


//        String msg = "{\n" +
//                "    \"msg\":{\n" +
//                "        \"attributeOrderType\":1,\n" +
//                "        \"attributeTime\":1631154176633,\n" +
//                "        \"attributeUid\":14077353,\n" +
//                "        \"buyerId\":14077353,\n" +
//                "        \"clickTime\":1630900035067,\n" +
//                "        \"extra\":\"{\\\"yzkEx\\\":\\\"3:a3a663e1-fca4-42f4-a441-290e22dde559:14077353:1:1630900035067:1:0\\\"}\",\n" +
//                "        \"kdtId\":13443333,\n" +
//                "        \"orderCreateTime\":1631154175000,\n" +
//                "        \"orderNo\":\"E202109091022540425001311\",\n" +
//                "        \"sendTime\":1631154176637\n" +
//                "    }\n" +
//                "}";

        String msg = "{\n" +
                "    \"attributeOrderType\":1,\n" +
                "    \"attributeTime\":1631154176633,\n" +
                "    \"attributeUid\":14077353,\n" +
                "    \"buyerId\":14077353,\n" +
                "    \"clickTime\":1630900035067,\n" +
                "    \"extra\":\"{\\\"yzkEx\\\":\\\"3:a3a663e1-fca4-42f4-a441-290e22dde559:14077353:1:1630900035067:1:0\\\"}\",\n" +
                "    \"kdtId\":13443333,\n" +
                "    \"orderCreateTime\":1631154175000,\n" +
                "    \"orderNo\":\"E202109091022540425001311\",\n" +
                "    \"sendTime\":1631154176637\n" +
                "}";

        Object maps = JSON.parseObject(msg, Map.class);


        System.out.println(maps);

        String script = "long time = msg.orderCreateTime;" +
                "System.out.println(time)" +
                "Map param = new HashMap();\n" +
                "param.put(\"yzUid\", 14077353);\n" +
                "param.put(\"time\", new Date(1631154175000/1000));\n" +
                "return param;\n";



        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("msg",maps);

        Object res = engine.executeRule(script,paramMap,"TEST");



        //2021-09-09
        Date date1 = new Date(1631154175000L);



        System.out.println(res);




    }
}
