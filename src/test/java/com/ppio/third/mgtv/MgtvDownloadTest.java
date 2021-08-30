package com.ppio.third.mgtv;


import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MgtvDownloadTest {

    @Autowired
    private OkHttpClient okHttpClient;

    @Test
    public void test1() throws Exception {

        Request.Builder builder = new Request.Builder();
        builder.url("https://ks-error-data.oss-cn-hangzhou.aliyuncs.com/20210822/500err.txt");
        Request request = builder.build();
        Response response = okHttpClient.newCall(request).execute();
//        log.info(response.toString());

        String dir = "./file/";
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }

        InputStream is = response.body().byteStream();
        FileOutputStream fos = new FileOutputStream(new File(dir + "500err.txt"));
        int len;
        byte[] buffer = new byte[2048];
        while (-1 != (len = is.read(buffer))) {
            fos.write(buffer, 0, len);
        }
        fos.flush();
        fos.close();
        is.close();

    }


    // curl --user test:VC4LRAMXpzX3fHECcZ1g  "http://221.229.163.80:20000/download.do?f=access.log.202108201700.gz"
    @Test
    public void test2() throws Exception {

        Request.Builder builder = new Request.Builder();
//        builder.url("http://221.229.163.80:20000/download.do?f=access.log.202108201700.gz");
        builder.url("http://221.229.163.80:20000/download.do?f=access.log.202108182200.gz");
        byte[] pwd = Base64.getEncoder().encode("test:VC4LRAMXpzX3fHECcZ1g".getBytes());
        builder.header("Authorization", "Basic " + new String(pwd));
        Request request = builder.build();
        Response response = okHttpClient.newCall(request).execute();
        log.info(response.toString());

    }


}
