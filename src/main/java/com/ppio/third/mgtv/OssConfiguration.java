package com.ppio.third.mgtv;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OssConfiguration {


    // Endpoint以杭州为例，其它Region请按实际情况填写。
    @Value("${oss.endpoint}")
    private String endpoint;
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    @Value("${oss.access.key}")
    private String accessKeyId;
    @Value("${oss.access.secret}")
    private String accessKeySecret;
    @Value("${oss.bucket.name}")
    String bucketName;
    // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。

    public OSS getOssClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
