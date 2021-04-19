//package com.pc.i18n;
//
//import com.twodfire.share.result.Result;
//import com.twodfire.share.util.ResultUtil;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.TimeZone;
//
///**
// * TODO
// *
// * @author pengchao
// * @date 15:36 2020-08-24
// */
//public class TestDemo {
//
//    private static SimpleDateFormat SDF_TIME_ZONE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    public static void main(String[] args) {
//
//        Date date = new Date();
//        try {
//            String str = "fafadf";
//            if (str!=null) {
//                Date tempDate = new Date();
//                SDF_TIME_ZONE.setTimeZone(TimeZone.getTimeZone(getAndCheckShopTimeZone(str)));
//                date = SDF.parse(SDF_TIME_ZONE.format(tempDate));
//            }
//        } catch (ParseException e) {
//            System.err.println(String.format("计算店铺国家化运营时间出错！result：%s",e));
//
//            date = new Date();
//        }
//
//        System.out.println(date);
//    }
//
//
//    public static String getAndCheckShopTimeZone(String timeZone) {
//        //默认时区
//        String defaultTimeZone = "GMT+8";
//        try {
//            if (timeZone!=null) {
//                //获取前缀
//                String prefix = "";
//                String suffix = "";
//                if (timeZone.contains("+")) {
//                    prefix = timeZone.substring(0, timeZone.indexOf("+") + 1);
//                    suffix = timeZone.substring(timeZone.indexOf("+") + 1);
//                } else if (timeZone.contains("-")) {
//                    prefix = timeZone.substring(0, timeZone.indexOf("-") + 1);
//                    suffix = timeZone.substring(timeZone.indexOf("-") + 1);
//                } else {
//                    return defaultTimeZone;
//                }
//                if (suffix.length() == 1) {
//                    timeZone = prefix + suffix;
//                } else if (suffix.length() >= 2) {
//                    suffix = suffix.substring(0, 2);
//                    if (suffix.contains("_")) {
//                        suffix = suffix.substring(0, suffix.indexOf("_"));
//                        timeZone = prefix + suffix;
//                    } else {
//                        //  判断hou 是否大于12（最大正12时区)
//                        Integer num = Integer.valueOf(suffix);
//                        if (num <= 12) {
//                            //返回默认
//                            timeZone = prefix + num;
//                        } else {
//                            // 取一位
//                            suffix = suffix.substring(0, 1);
//                            timeZone = prefix + suffix;
//                        }
//                    }
//                }
//                return timeZone;
//            }
//        } catch (Exception e) {
//            return defaultTimeZone;
//        }
//        return defaultTimeZone;
//    }
//}
