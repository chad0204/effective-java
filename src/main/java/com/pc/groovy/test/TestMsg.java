package com.pc.groovy.test;

import com.alibaba.fastjson.JSON;
import com.pc.groovy.GroovyCheckRuleEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-09-15 9:04 下午
 */
public class TestMsg {

    public static void main(String[] args) {

        GroovyCheckRuleEngine engine = new GroovyCheckRuleEngine();



        String msg = "{\n" +
                "    \"orderType\":\"NORMAL\",\n" +
                "    \"logisticsDTO\":{\n" +
                "        \"receiverName\":\"\",\n" +
                "        \"extra\":{\n" +
                "\n" +
                "        },\n" +
                "        \"logisticsType\":\"NONE\"\n" +
                "    },\n" +
                "    \"orderId\":2830116146716344379,\n" +
                "    \"payTime\":1632639474588,\n" +
                "    \"buyerDTO\":{\n" +
                "        \"buyerPhone\":\"15552578777\",\n" +
                "        \"fansId\":0,\n" +
                "        \"beneficiary\":\"\",\n" +
                "        \"buyerId\":950950340,\n" +
                "        \"fansType\":0\n" +
                "    },\n" +
                "    \"memo\":\"\",\n" +
                "    \"orderStatus\":\"PAID\",\n" +
                "    \"payWay\":\"MARK_PAY_WXPAY\",\n" +
                "    \"channelType\":\"YOUZAN\",\n" +
                "    \"source\":\"{\\\"clientIp\\\":\\\"127.0.0.1\\\",\\\"isOnlineOrder\\\":false,\\\"newSource\\\":\\\"{\\\\\\\"platformEnum\\\\\\\":\\\\\\\"OTHER\\\\\\\",\\\\\\\"wxEntranceEnum\\\\\\\":\\\\\\\"DIRECT_BUY\\\\\\\"}\\\",\\\"source\\\":\\\"ANDROID-RETAILHD-6.60.0\\\"}\",\n" +
                "    \"priceDTO\":{\n" +
                "        \"postage\":0,\n" +
                "        \"totalPrice\":55800,\n" +
                "        \"currentPrice\":55800,\n" +
                "        \"originPrice\":60000,\n" +
                "        \"promotionAmount\":0\n" +
                "    },\n" +
                "    \"couponAmount\":0,\n" +
                "    \"payType\":5,\n" +
                "    \"bizCategory\":\"RETAIL\",\n" +
                "    \"extra\":{\n" +
                "        \"extend_version\":\"2\",\n" +
                "        \"MINUS_STOCK_DEDUCT\":\"1\",\n" +
                "        \"IS_ALL_SUB_ORDER_CANCEL\":\"1\",\n" +
                "        \"IS_MEMBER\":\"true\",\n" +
                "        \"PRRINCIPAL_CERT_TYPE\":\"3\",\n" +
                "        \"NEED_STOCK_UP\":\"0\",\n" +
                "        \"IS_USE_PARAM_PRICE\":\"1\",\n" +
                "        \"CASHIER_NAME\":\"董海伟\",\n" +
                "        \"ORDER_FROM_METHOD\":\"simpleCreate\",\n" +
                "        \"IS_SPLIT_STOCK_DEDUCT\":\"true\",\n" +
                "        \"BRAND_CERT_TYPE\":\"0\",\n" +
                "        \"OWNER_ID\":\"0\",\n" +
                "        \"FEE_MIGRATE_CHARGE_BY_TC\":\"true\",\n" +
                "        \"PARENT_KDT_ID\":\"41202174\",\n" +
                "        \"excludePayToolCode\":\"49,40\",\n" +
                "        \"BUYER_BENIFIT\":\"{\\\"level\\\":\\\"1_3\\\",\\\"type\\\":\\\"2\\\"}\",\n" +
                "        \"ACTIVITY_TYPE\":\"10\",\n" +
                "        \"IS_TOURIST_BUYER\":\"false\",\n" +
                "        \"TOTAL_PRICE\":\"{\\\"currentPrice\\\":55800,\\\"originPrice\\\":60000}\",\n" +
                "        \"CREATE_BY_NEW_TABLE\":\"1\",\n" +
                "        \"OUTER_TRANSACTION_NO\":\"v210926145754000147\",\n" +
                "        \"srcEnv\":\"prod\",\n" +
                "        \"SHOP_TOPIC\":\"0\",\n" +
                "        \"BUYER_PHONE\":\"15552578777\",\n" +
                "        \"IS_BOS_FLOW\":\"false\",\n" +
                "        \"excludePayTool\":\"INSTALMENT,PRIOR_USE\",\n" +
                "        \"mchId\":\"\",\n" +
                "        \"ENABLE_ACROSS_SHOP_VERIFY\":\"1\",\n" +
                "        \"ORDER_STOCK_NEW_REQUEST_OFC\":\"1\",\n" +
                "        \"bankCardWaterNo\":\"\",\n" +
                "        \"ORDER_REQUEST_OFC\":\"1\",\n" +
                "        \"CREATE_DEVICE_ID\":\"Androidcfd7e57ab50986bea40dc44342f32c06\",\n" +
                "        \"CASHIER_ID\":\"496083994\",\n" +
                "        \"WEAPP_TRADE_MODULE_STATUS\":\"0\",\n" +
                "        \"WEAPP_TRADE_MODULE_TICKET\":\"0\",\n" +
                "        \"SHOP_ROLE\":\"2\",\n" +
                "        \"IS_NEW_RETAIL\":\"1\",\n" +
                "        \"BIZ_ORDER_ATTRIBUTE\":\"{\\\"RETAIL_ORDER_TYPE\\\":\\\"0\\\"}\",\n" +
                "        \"payTool\":\"MARK_PAY\",\n" +
                "        \"STOCK_DEDUCT_SCENE\":\"0\",\n" +
                "        \"WECHAT_SYNC_SHOPPING_LIST\":\"0\",\n" +
                "        \"AD_CPS_SHOP\":\"0\",\n" +
                "        \"FROM_CART\":\"false\",\n" +
                "        \"REAL_PAY_AMOUNT\":\"55800\",\n" +
                "        \"INNER_TRANSACTION_NO\":\"210926145754000144\",\n" +
                "        \"BUYER_NAME\":\"小虾米\"\n" +
                "    },\n" +
                "    \"closeTypeValue\":0,\n" +
                "    \"orderItemDTOGroup\":[\n" +
                "        {\n" +
                "            \"realPay\":55800,\n" +
                "            \"skuDTO\":{\n" +
                "                \"imageUrl\":\"https://img.yzcdn.cn/upload_files/2021/07/21/FpAvEumP4TU4ZarWNjabTJlnijem.png\",\n" +
                "                \"name\":\"香格里拉野生松茸\",\n" +
                "                \"skuMap\":\"[{\\\"k\\\":\\\"规格\\\",\\\"k_id\\\":14,\\\"v\\\":\\\"5-7cm 1kg\\\",\\\"v_id\\\":20868174}]\",\n" +
                "                \"currentPrice\":55800,\n" +
                "                \"originPrice\":60000,\n" +
                "                \"skuCompositeId\":{\n" +
                "                    \"goodsId\":1029631670,\n" +
                "                    \"skuId\":37437731\n" +
                "                },\n" +
                "                \"type\":\"COMMON\",\n" +
                "                \"skuCode\":\"P210721142863422\"\n" +
                "            },\n" +
                "            \"extra\":{\n" +
                "                \"STOCK_DEDUCT_SCENE\":\"0\",\n" +
                "                \"ORDER_ITEM_UNIQUE_KEY\":\"1029631670_37437731_10_0__0_0\",\n" +
                "                \"USED_PRO\":\"{\\\"activityId\\\":0,\\\"activityType\\\":\\\"10\\\",\\\"promotionId\\\":0,\\\"promotionName\\\":\\\"会员价\\\",\\\"promotionTypeId\\\":10,\\\"value\\\":4200}\",\n" +
                "                \"GOODS_PROPERTY\":\"null\",\n" +
                "                \"ITEM_PRICE\":\"{\\\"currentPrice\\\":55800,\\\"currentTotalAmount\\\":55800,\\\"originPrice\\\":60000,\\\"originTotalAmount\\\":60000}\"\n" +
                "            },\n" +
                "            \"num\":1,\n" +
                "            \"snapShot\":\"daad9e784e2c9b8e51310f529eb81587\",\n" +
                "            \"memo\":\"\",\n" +
                "            \"quotaNum\":0,\n" +
                "            \"id\":2830116146716344380,\n" +
                "            \"goodsInfo\":{\n" +
                "                \"pricing_strategy\":0,\n" +
                "                \"category_name\":\"传统滋补\",\n" +
                "                \"extraMap\":{\n" +
                "                    \"ITEM_UNIQ_ID\":\"100000\",\n" +
                "                    \"ABILITY_MARK_CODES\":\"[30005,10005,40001,40002,40003,40005,40006,40008,40013,30002,10023]\"\n" +
                "                },\n" +
                "                \"needCustomsCheck\":false,\n" +
                "                \"spec\":\"5-7cm 500g\",\n" +
                "                \"needCustomsPicture\":false,\n" +
                "                \"isSupportInstallment\":false,\n" +
                "                \"quota\":0,\n" +
                "                \"needCustomsInfo\":false,\n" +
                "                \"buy_way\":1,\n" +
                "                \"goods_id\":1029631670,\n" +
                "                \"new_goods_no\":\"P210721095960879\",\n" +
                "                \"quotaType\":\"NO\",\n" +
                "                \"unit\":\"件\",\n" +
                "                \"points_price\":0,\n" +
                "                \"class2\":\"\",\n" +
                "                \"img_url\":\"https://img.yzcdn.cn/upload_files/2021/07/21/FpAvEumP4TU4ZarWNjabTJlnijem.png\",\n" +
                "                \"class1\":8,\n" +
                "                \"brandId\":0,\n" +
                "                \"stock_unit\":\"件\",\n" +
                "                \"is_virtual\":0,\n" +
                "                \"bizMarkCode\":\"000000000000\",\n" +
                "                \"goods_no\":\"P210721095960879\",\n" +
                "                \"title\":\"香格里拉野生松茸\",\n" +
                "                \"category_id\":8706905,\n" +
                "                \"extra\":{\n" +
                "                    \"weight\":0\n" +
                "                },\n" +
                "                \"categoryList\":[\n" +
                "                    \"其他\"\n" +
                "                ],\n" +
                "                \"alias\":\"1y5h2wrm0d07q\",\n" +
                "                \"parentSkuId\":37363115,\n" +
                "                \"parentGoodsId\":1009058326,\n" +
                "                \"mark\":0\n" +
                "            },\n" +
                "            \"tags\":{\n" +
                "                \"STOCK_DEDUCTED\":true\n" +
                "            },\n" +
                "            \"usedPromotion\":{\n" +
                "                \"promotionTypeId\":10,\n" +
                "                \"promotionId\":0,\n" +
                "                \"activityId\":0,\n" +
                "                \"promotionName\":\"会员价\",\n" +
                "                \"value\":4200,\n" +
                "                \"activityType\":\"10\"\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"id\":2830116146716344379,\n" +
                "    \"closeReason\":\"\",\n" +
                "    \"salesModelType\":0,\n" +
                "    \"phase\":1,\n" +
                "    \"bizTags\":{\n" +
                "        \"normal\":true,\n" +
                "        \"fx\":false,\n" +
                "        \"edu\":false,\n" +
                "        \"purchase\":false,\n" +
                "        \"flowTypeValue\":889454592,\n" +
                "        \"multiGoodsTypeModelType\":0,\n" +
                "        \"salesModelType\":0,\n" +
                "        \"fxModeType\":0\n" +
                "    },\n" +
                "    \"orderNo\":\"E20210926145754045200001\",\n" +
                "    \"visible\":true,\n" +
                "    \"sellerDTO\":{\n" +
                "        \"payKdtId\":78129318,\n" +
                "        \"kdtId\":78129318,\n" +
                "        \"rootKdtId\":41202174,\n" +
                "        \"shopName\":\"物本源济南门店\",\n" +
                "        \"shopId\":0,\n" +
                "        \"shopType\":\"NORMAL\",\n" +
                "        \"teamType\":\"CJMD\"\n" +
                "    },\n" +
                "    \"outBizNo\":\"R2021092614575411078493070067\",\n" +
                "    \"updateTime\":1632639474430,\n" +
                "    \"closeType\":\"NORMAL\",\n" +
                "    \"consumeStatus\":\"\",\n" +
                "    \"expiredTime\":1632646674323,\n" +
                "    \"goodsType\":0,\n" +
                "    \"tags\":{\n" +
                "        \"DELIVERY_OFC_ORDER\":true,\n" +
                "        \"STOCK_DEDUCTED\":true,\n" +
                "        \"IS_MEMBER\":true,\n" +
                "        \"IS_SECURED_TRANSACTIONS\":true,\n" +
                "        \"IS_PAYED\":true\n" +
                "    },\n" +
                "    \"createTime\":1632639474000,\n" +
                "    \"contractId\":2830116146716344378,\n" +
                "    \"sentTime\":1632639474894,\n" +
                "    \"payId\":\"210926145754000139\",\n" +
                "    \"activityType\":1,\n" +
                "    \"logisticsType\":3\n" +
                "}";

        Map msgMap = JSON.parseObject(msg, Map.class);



        Map<String,Object> param = new HashMap<>();
        param.put("msg",msgMap);



        String content = "import groovy.json.JsonSlurper;\n" +
                "//直播场景订单, scene 1177\n" +
                "\n" +
                "if (msg.orderItemDTOGroup == null || msg.orderItemDTOGroup.size() < 1 || msg.orderItemDTOGroup[0].extra == null) {\n" +
                "  return false;\n" +
                "}\n" +
                "\n" +
                "def extra = msg.orderItemDTOGroup[0].extra.BIZ_TRACE_POINT;\n" +
                "\n" +
                "if (extra == null) {\n" +
                "  return false;\n" +
                "}\n" +
                "\n" +
                "def extra_json = new JsonSlurper().parseText(extra);\n" +
                "\n" +
                "// return extra_json.extension.scene;\n" +
                "if(\"1177\" == extra_json.extension.scene){\n" +
                "  return true;\n" +
                "} else{\n" +
                "  return false;\n" +
                "}";


        Object res = engine.executeRule(content,param,"TEST");

        System.out.println(res);


    }



}


