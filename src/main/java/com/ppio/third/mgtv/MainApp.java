package com.ppio.third.mgtv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * nohup java -jar -Dspring.profiles.active=prd    mgtvAccesslogSync-1.0-SNAPSHOT.jar > /dev/null 2>&1  &
 * <p>
 * prd 部署在机器
 * /opt/iaas/mgtvAccesslogSync
 * [root@pi-bigdata-pcdn-log014-ali-zjhz mgtvAccesslogSync]
 */

/**
 *
 * http://223.112.135.15:20000/download.do?f=access.log.202109291700.gz
 * 手动下载
 * curl --user ppio:UP32s7y1u#LXN%Kd "http://223.112.135.15:20000/download.do?f=access.log.202109291700.gz" --output "1.gz"
 *
 * curl --user ppio:UP32s7y1u#LXN%Kd "http://120.238.122.235:20000/download.do?f=access.log.202109081300.gz" --output "2.gz"
 */
@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = {"com.ppio.third.mgtv.mapper"})
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
