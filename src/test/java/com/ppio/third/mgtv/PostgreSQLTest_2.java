package com.ppio.third.mgtv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLTest_2 {


    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;

        String sql = "SELECT  DISTINCT machine_id\n" +
                "        ,ip\n" +
                "FROM    t_ods_machine_line_net_check\n" +
                "WHERE   dt = '20210827'\n" +
                "AND     machine_id IN ( SELECT DISTINCT machine_id FROM t_ods_trafficv4_docker WHERE dt = '20210827' AND name = 'tx' )";
        try {
            Class.forName("org.postgresql.Driver");

//            System.out.println(Driver.class.getName());
            conn = DriverManager.getConnection("jdbc:postgresql://hgmc-cn-st21vo0do001-cn-hangzhou.hologres.aliyuncs.com:80/db_prd?preferQueryMode=simple&tcpKeepAlive=true", "----", "----");
            System.out.println("连接数据库成功!");
            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from t_ads_business_bw_allocated where dt = '20201001' limit 10;");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("machine_id");
                String ip = rs.getString("ip");
                System.out.println("machine_id=" + id + "  ip=" + ip);

            }
            rs.close();
            stmt.close();

//            System.out.println("-------------------------------------------+++++++++++++++++++++++++++++++++");
//
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("select * from t_ads_business_bw_allocated where dt = '20201001' limit 10;");
//            while (rs.next()) {
//                String id = rs.getString("machine_id");
//                String name = rs.getString("name");
//                System.out.println("id=" + id + "  name=" + name);
//
//            }


            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
