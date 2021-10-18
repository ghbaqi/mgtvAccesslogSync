package com.ppio.third.mgtv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Filetest {
    public static void main(String[] args) {

        try {
            FileReader fr = new FileReader("/Users/gaohui/Desktop/2");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
//                String host = str.split("`")[22];
                String host = str.split("\"`\"")[22];
//                System.out.println(host);
                if (!host.contains("non80.openredcloud.com")) {
                    System.out.println(str);
                }
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
