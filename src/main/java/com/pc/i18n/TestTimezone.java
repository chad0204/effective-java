package com.pc.i18n;

import com.pc.something.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * TODO
 *
 * @author pengchao
 * @date 10:15 2020-08-24
 */
public class TestTimezone {

    static String defaultTimeZone = "GMT+10";

    public static void main(String[] args) throws ParseException {

        Date date = new Date();  // 对应的北京时间是

        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区

        SimpleDateFormat tokyoSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // 东京
        tokyoSdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));  // 设置东京时区

        SimpleDateFormat londonSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 伦敦
        londonSdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));  // 设置伦敦时区

        System.out.println("毫秒数:" + date.getTime() + ", 北京时间:" + bjSdf.format(date));
        System.out.println("毫秒数:" + date.getTime() + ", 东京时间:" + tokyoSdf.format(date));
        System.out.println("毫秒数:" + date.getTime() + ", 伦敦时间:" + londonSdf.format(date));




        SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 伦敦
        test.setTimeZone(TimeZone.getTimeZone(defaultTimeZone));  // 设置伦敦时区
        System.out.println("毫秒数:" + date.getTime() + ", GMT时间:" + test.format(date));





        Date beforeDate = new Date();

        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SDF.setTimeZone(TimeZone.getTimeZone(defaultTimeZone));

        String s = SDF.format(beforeDate);


        SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date afterDate = SDF.parse(s);


        System.out.println(s);
        System.out.println(beforeDate);
        System.out.println(afterDate);





    }
}
