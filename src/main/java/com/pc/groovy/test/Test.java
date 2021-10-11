package com.pc.groovy.test;

import com.alibaba.fastjson.JSON;
import com.pc.groovy.GroovyCheckRuleEngine;
import groovy.transform.ToString;
import groovy.transform.builder.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengchao
 * @description:
 * @date 2021-09-15 9:04 下午
 */
public class Test {

    public static void main(String[] args) {







        //业务消息
        String str = "{\"s3\":\"0\",\"created_time\":\"1626747983\",\"s4\":\"0\",\"s5\":\"0\",\"stock_mode\":\"0\",\"stock_num\":\"76\",\"channel\":\"0\",\"goods_id\":\"1006266703\",\"stock_type\":\"0\",\"kdt_id\":\"90314643\",\"update_time\":\"1631851829\",\"id\":\"37348816\",\"stock_related_mode\":\"0\",\"stock_scene\":\"0\",\"s1\":\"137\",\"s2\":\"169\"}";

        //bcp中，消息是转成map来执行的
        Map msg = JSON.parseObject(str, Map.class);


        //binlog消息
        String binlog = "{\"rdsSchemaName\":\"sc\",\"add_mq_time\":1631759072690,\"binlogTime\":1631759072000,\"before\":{},\"binlogSequenceNo\":1631759071876000022,\"eventType\":\"INSERT\",\"schemaName\":\"sc334\",\"binlogOffset\":174728727,\"binlogTransactionNo\":1163175907200002,\"binlogTableName\":\"goods_sku\",\"binlogFileName\":\"mysql-bin.000059\",\"binlogApplyTime\":1631759072690,\"after\":{\"out_sold_num\":\"0\",\"code\":\"\",\"stock_mode\":\"0\",\"discount_price\":\"0\",\"stock_num\":\"4000\",\"channel\":\"0\",\"discount\":\"100\",\"hot\":\"0\",\"pre_occupied_stock\":\"0\",\"send_num\":\"0\",\"kdt_id\":\"42870434\",\"update_time\":\"1631759072\",\"managed_stock_num\":\"0\",\"price\":\"20000\",\"actual_sold_num\":\"0\",\"id\":\"37525747\",\"sku\":\"[{\\\"k\\\":\\\"颜色\\\",\\\"k_id\\\":1,\\\"v\\\":\\\"红色\\\",\\\"v_id\\\":1215}]\",\"stock_related_mode\":\"1\",\"barcode\":\"\",\"s1\":\"1215\",\"s2\":\"0\",\"s3\":\"0\",\"out_actual_sold_num\":\"0\",\"ext\":\"\",\"s4\":\"0\",\"created_time\":\"1631759072\",\"sell_stock_mode\":\"0\",\"s5\":\"0\",\"goods_id\":\"1074043109\",\"version\":\"0\",\"sold_num\":\"0\",\"sku_change_version\":\"0\",\"stock_type\":\"0\",\"order_num\":\"0\",\"stock_scene\":\"0\"},\"nsqTraceId\":1631759072690}";

        Map binlogMsg = JSON.parseObject(binlog, Map.class);



        List<Response> list = new ArrayList<>();
        list.add(new Response("1","data"));
        list.add(new Response("2","data"));


        Map<String,Object> param = new HashMap<>();
        InnerResponse innerResponse = new InnerResponse("myValue");
        param.put("returnValueAlias",new Response("100","2333",innerResponse));
        param.put("msg",msg);
        param.put("binlogMsg",binlogMsg);

        param.put("list",list);



        String content = "list.each{language->\n" +
                " if (language.code=='code') " +
                "System.out.print('ok')\n" +
                "};";

        String scp = "list.getAt('code')";


        GroovyCheckRuleEngine engine = new GroovyCheckRuleEngine();
        Object res = engine.executeRule(content,param,"TEST");

        System.out.println(res);


    }

    static class Response {
        String code;
        String data;
        InnerResponse innerResponse;

        public Response(String code, String data, InnerResponse innerResponse) {
            this.code = code;
            this.data = data;
            this.innerResponse = innerResponse;
        }

        public Response(String code, String data) {
            this.code = code;
            this.data = data;
        }
    }

    static class InnerResponse {
        String value;

        public InnerResponse(String value) {
            this.value = value;
        }
    }

}


