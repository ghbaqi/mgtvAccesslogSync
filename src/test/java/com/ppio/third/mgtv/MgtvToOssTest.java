package com.ppio.third.mgtv;


import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.Base64;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MgtvToOssTest {


    @Autowired
    private OssConfiguration ossConfiguration;

    @Autowired
    private OkHttpClient okHttpClient;

    // curl --user test:VC4LRAMXpzX3fHECcZ1g  "http://221.229.163.80:20000/download.do?f=access.log.202108201700.gz"
    @Test
    public void test1() throws Exception {

        Request.Builder builder = new Request.Builder();
        builder.url("http://221.229.163.80:20000/download.do?f=access.log.202108201700.gz");
        byte[] pwd = Base64.getEncoder().encode("test:VC4LRAMXpzX3fHECcZ1g".getBytes());
        builder.header("Authorization", "Basic " + new String(pwd));
        Request request = builder.build();
        Response response = okHttpClient.newCall(request).execute();
        log.info(response.toString());


        log.info("往 oss 发送 {} 数据 ");
        String objectName = "202108011220.gz";
        InputStream is = response.body().byteStream();
        OSS oss = ossConfiguration.getOssClient();
        PutObjectResult result = oss.putObject("mgtv-access-log", objectName, is);
        log.info(result.toString());
        oss.shutdown();

    }
}
