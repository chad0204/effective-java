package com.pc.i18n;

import com.dfire.consumer.i18n.code.soa.ConsumerSOACode;
import com.twodfire.share.result.Result;
import com.twodfire.share.util.ResultUtil;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * TODO
 *
 * @author pengchao
 * @date 10:29 2020-08-20
 */
public class Test {

    public static void main(String[] args) {

        //
        System.out.println(ConsumerSOACode.ERR_CSS001.getErrCode());
        System.out.println(ConsumerSOACode.ERR_CSS001.getMessage());

        System.out.println("--");
        //
        System.out.println(ConsumerSOACode.MULTI_CSS008.getErrCode());
        System.out.println(ConsumerSOACode.MULTI_CSS008.getMessage());
        System.out.println(ConsumerSOACode.MULTI_CSS008.getI18nCode());
        System.out.println(ConsumerSOACode.MULTI_CSS008.getMultiCode());
        System.out.println(ConsumerSOACode.MULTI_CSS008.getKeepCode());

        Result result = ResultUtil.failResult(ConsumerSOACode.MULTI_CSS517.getErrCode(), ConsumerSOACode.MULTI_CSS517.getMessage(),true);


        System.out.println(result.getI18nCode());


        Date date = new Date();
        Locale locale = Locale.CHINA;
        DateFormat shortDf = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL, locale);
        shortDf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));//Asia/Chongqing
        System.out.println(TimeZone.getDefault().getID());
        System.out.println("中国当前日期时间：" + shortDf.format(date));

        locale = Locale.ENGLISH;
        shortDf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM, locale);
        shortDf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        System.out.println("英国当前日期时间："+shortDf.format(date));




        //GMT+8
        shortDf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
        shortDf.setTimeZone(TimeZone.getTimeZone("GMT+:08:00"));
        System.out.println("英国当前日期时间："+shortDf.format(date));






    }




}
