package com.ppio.third.mgtv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QualityDataTest {

    public static void main(String[] args) throws Exception {
//        String s = "2021-10-15 04:45:00\t江苏\t移动\tPC-PPIO江苏移动\t0.29%\t0%";
//        for (String a : s.split("\t")) {
//            System.out.println(a);
//        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse("2021-10-15 04:45:00");
        System.out.println(d);
    }

    @Autowired
    SyncService service;

    //-- 江苏移动质量数据地址:http://39.96.63.155/ppio-cmnet-jiangsu/quality.txt.latest
    //-- 芒果质量 接口数据 。
    //-- 湖南移动质量数据地址:http://39.96.63.155/ppio-cmnet-hunan/quality.txt.latest
    //-- 河南联通质量数据地址:http://39.96.63.155/ppio-cnc-henan/quality.txt.latest
    @Test
    public void t() throws Exception {
        service.getQualityData("http://39.96.63.155/ppio-cnc-henan/quality.txt.latest");
    }
}
