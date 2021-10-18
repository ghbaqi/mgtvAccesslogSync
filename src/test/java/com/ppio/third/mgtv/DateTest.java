package com.ppio.third.mgtv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateTest {
    public static void main(String[] args) throws Exception {


        //dd/MMM/yyyy:HH:mm:ss
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss");
//        Date d1 = dateFormat.parse("08/Jul/2021:17:05:26");
//        System.out.println(d1);

        // 函数名称  mgtv_get_timeid(String in)
        String in = "18/Sep/2017:06:42:18 +0800";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss Z", Locale.US);
        Date date = sdf1.parse(in);

        long a1 = date.getTime() - date.getTime() % (5 * 60 * 1000);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmm");
        String time_id = format.format(new Date(a1));

        System.out.println(time_id);


    }
}
