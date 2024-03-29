package com.ppio.third.mgtv;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import com.ppio.third.mgtv.bean.AccessUrlBean;
import com.ppio.third.mgtv.entity.TMgtvAccesslogItems;
import com.ppio.third.mgtv.entity.TMgtvAccesslogMachine;
import com.ppio.third.mgtv.entity.TThirdMgtvQuality;
import com.ppio.third.mgtv.mapper.TMgtvAccesslogItemsMapper;
import com.ppio.third.mgtv.mapper.TMgtvAccesslogMachineMapper;
import com.ppio.third.mgtv.mapper.TThirdMgtvQualityMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.postgresql.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.notification.ModelMBeanNotificationPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
@Slf4j
public class SyncService {

    @Autowired
    private OkHttpClient okHttpClient;

    @Value("${mgtv.authorization}")
    private String mgtvAuthorization;

    @Autowired
    private TMgtvAccesslogItemsMapper accesslogItemsMapper;

    @Autowired
    private TMgtvAccesslogMachineMapper machineMapper;

    @Autowired
    private OssConfiguration ossConfiguration;

    @Value("${mgtv.authorization}")
    private String authorization;

    @Value("${oss.bucket.name}")
    private String bucketName;

    @Value("${oss.access.key}")
    private String accessKeyId;
    @Value("${oss.access.secret}")
    private String accessKeySecret;


    /**
     * 同步一个 access log 到 oss  上
     */
    public void sync(String machineId, AccessUrlBean urlBean) {

        TMgtvAccesslogItems existlogItem = accesslogItemsMapper.selectByMachineidAndFilename(machineId, urlBean.getName());
        if (existlogItem != null && existlogItem.getStatus() != UploadStatusEnum.Failure.getStatus()) {
            // 正在 上传 或者 已经上传成功
            return;
        }

        if (existlogItem == null) {
            existlogItem = new TMgtvAccesslogItems();
            existlogItem.setMachineId(machineId);
            existlogItem.setFileName(urlBean.getName());
            existlogItem.setUrl(urlBean.getUrl());
            existlogItem.setStatus(UploadStatusEnum.UploadLoading.getStatus());
            accesslogItemsMapper.insert(existlogItem);// 设置 状态未 正在上传
        } else {
            existlogItem.setUrl(urlBean.getUrl());
            existlogItem.setStatus(UploadStatusEnum.UploadLoading.getStatus());
            accesslogItemsMapper.updateByPrimaryKey(existlogItem);

        }

        // 上传中 状态
        //  下载 和 上传
        String fileName = urlBean.getName(); // access.log.202108200700.gz
        String url = urlBean.getUrl();
        Response response = null;
        try {
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            builder.header("Authorization", authorization);
            Request request = builder.build();
            response = okHttpClient.newCall(request).execute();
            if (!response.isSuccessful() || response.code() != 200) {
                log.error("下载 access log 文件错误 machine = {} ,  url = {} , response = {}", machineId, url, response.toString());
                existlogItem.setStatus(UploadStatusEnum.Failure.getStatus());
                accesslogItemsMapper.updateByPrimaryKey(existlogItem);
                return;
            }

        } catch (IOException e) {
            log.error("下载 access log 文件错误 urlBean = {} , error = {}", urlBean, e);
            // 失败 状态维护
            existlogItem.setStatus(UploadStatusEnum.Failure.getStatus());
            accesslogItemsMapper.updateByPrimaryKey(existlogItem);
            return;
        }


        try {
            log.info("下载 access log 成功 ，往 oss 发送 {} 数据 ");
            String dt = fileName.substring(11, 19);
            fileName = machineId + "." + fileName;
            InputStream is = response.body().byteStream();
            OSS oss = ossConfiguration.getOssClient();
            oss.putObject(bucketName, dt + "/" + fileName, is);
            oss.shutdown();
            existlogItem.setStatus(UploadStatusEnum.Success.getStatus());
            accesslogItemsMapper.updateByPrimaryKey(existlogItem);
            log.info("success machine = {} , url = {}", machineId, url);
            // 成功状态维护
            if (response != null)
                response.close();
        } catch (Exception e) {
            existlogItem.setStatus(UploadStatusEnum.Failure.getStatus());
            accesslogItemsMapper.updateByPrimaryKey(existlogItem);
            log.error("往 oss 发送失败 urlBean = {} ， e = {}", urlBean, e);
        }


    }

    public List<TMgtvAccesslogMachine> getOnlineMachines() {
        List<TMgtvAccesslogMachine> machines = machineMapper.getOnlineMachines();
        return machines;
    }


    /**
     * curl --user test:VC4LRAMXpzX3fHECcZ1g "http://221.229.163.80:20000/list.do"
     * 获取机器所有的 access 文件列表
     *
     * @return
     */
    public List<AccessUrlBean> getAccessUrlBeans(TMgtvAccesslogMachine machine) {

        List<AccessUrlBean> urlBeans = null;
        try {
            // 1. 获取当前 机器所有可供下载的 文件列表  ：  http://221.229.163.80:20000/list.do
            Request.Builder builder = new Request.Builder();
            builder.url("http://" + machine.getIp().trim() + ":20000/list.do");
            builder.header("Authorization", mgtvAuthorization);
            Request request = builder.build();
            Response response = null;
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful() && response.code() == 200) {
                byte[] bytes = response.body().bytes();
                String listJsonStr = new String(bytes);
                urlBeans = JSON.parseArray(listJsonStr, AccessUrlBean.class);

            } else {
                log.error("获取 access 文件列表错误1 ，machine = {} , response = {} ", machine, response.toString());
            }
            if (response != null)
                response.close();
        } catch (Exception e) {
            log.error("获取 access 文件列表错误2 ，error =  {} ", e);

        }

        return urlBeans;

    }


//    -- 查看 芒果所有的 机器 和 ip
//    SELECT  DISTINCT machine_id
//        ,ip
//    FROM    ppio_env_test.t_ods_machine_line_net_check
//    WHERE   dt = '20210827'
//    AND     machine_id IN ( SELECT DISTINCT machine_id FROM ppio_env_test.t_ods_trafficv4_docker WHERE dt = '20210827' AND name = 'tx' )
//            ;

    /**
     * mgtvdb
     * 维护所有线上 正在运行的 芒果 机器列表
     * 1. 获取所有 芒果机器 t_ods
     * 2. 获取机器 ip
     * 3. 测试每个 接口是否联通，入库
     */
    public void maintainMachines() {


        Connection conn = null;
        Statement stmt = null;

        String sql = "SELECT  DISTINCT machine_id\n" +
                "        ,ip\n" +
                "FROM    t_ods_machine_line_net_check\n" +
                "WHERE   dt = '%s'\n" +
                "AND     machine_id IN ( SELECT DISTINCT machine_id FROM t_ods_trafficv4_docker WHERE dt = '%s' AND name = 'mgtvdb' )";
        String dt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        sql = String.format(sql, dt, dt);

        List<TMgtvAccesslogMachine> onlineMachines = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://hgmc-cn-st21vo0do001-cn-hangzhou.hologres.aliyuncs.com:80/db_prd?preferQueryMode=simple&tcpKeepAlive=true", accessKeyId, accessKeySecret);
//            System.out.println("连接数据库成功!");
            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from t_ads_business_bw_allocated where dt = '20201001' limit 10;");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("machine_id");
                String ip = rs.getString("ip");
                if (StringUtils.isEmpty(id) || StringUtils.isEmpty(ip))
                    continue;
                TMgtvAccesslogMachine machine = new TMgtvAccesslogMachine();
                machine.setMachineId(id);
                machine.setIp(ip);
                onlineMachines.add(machine);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            log.error("查询 hologres e = {}", e);
        }

        // 除了判断线上的机器 ，还要对 已经入库的机器再次判断
        List<TMgtvAccesslogMachine> exitMachines = machineMapper.getOnlineMachines();
        if (exitMachines != null && exitMachines.size() > 0) {
            onlineMachines.addAll(exitMachines);
        }


        // 1. 获取当前 机器所有可供下载的 文件列表  ：  http://221.229.163.80:20000/list.do
        Request.Builder builder = new Request.Builder();

        for (TMgtvAccesslogMachine machine : onlineMachines) {
            builder.url("http://" + machine.getIp() + ":20000/list.do");
            builder.header("Authorization", mgtvAuthorization);
            Request request = builder.build();
            TMgtvAccesslogMachine existMachine = machineMapper.selectByMachineIP(machine.getMachineId(), machine.getIp());
            // 测试接口是否联通 并入库
            boolean enable = false;
            try {
                Response response = okHttpClient.newCall(request).execute();
                if (response.isSuccessful() && response.code() == 200) {
                    enable = true;
                }
                if (response != null)
                    response.close();
            } catch (IOException e) {
                enable = false;
            }

            if (existMachine == null) {
                machine.setEnable(enable);
                machineMapper.insert(machine);
            } else {
                existMachine.setEnable(enable);
                machineMapper.updateByPrimaryKey(existMachine);
            }
        }

    }


    @Autowired
    private TThirdMgtvQualityMapper qualityMapper;

    /**
     * 获取芒果 质量 接口数据
     * <p>
     * cHBpbzpVUDMyczd5MXUjTFhOJUtk
     */
    //-- 用户名：ppio
    //-- 密码：UP32s7y1u#LXN%Kd
    //-- 江苏移动质量数据地址:http://39.96.63.155/ppio-cmnet-jiangsu/quality.txt.latest
    //-- 芒果质量 接口数据 。
    //-- 湖南移动质量数据地址:http://39.96.63.155/ppio-cmnet-hunan/quality.txt.latest
    //-- 河南联通质量数据地址:http://39.96.63.155/ppio-cnc-henan/quality.txt.latest
    public void getQualityData(String url) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.header("Authorization", "Basic cHBpbzpVUDMyczd5MXUjTFhOJUtk");
        Request request = builder.build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody body = response.body();
            InputStream is = body.byteStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();

            // 2021-10-18 13:55:00	江苏	移动	PC-PPIO江苏移动	0.8%	0.16%
            TThirdMgtvQuality quality = new TThirdMgtvQuality();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (line != null && !line.trim().equals("")) {
                System.out.println(line);

                String[] arr = line.split("\t");
//                System.out.println("arr length " + arr.length);

                // 2021-11-29 00:20:00	PC-PPIO河南联通	0.66%	0%
                if (arr.length == 4) {
                    quality.setTime(sdf.parse(arr[0]));
                    quality.setResource(arr[1]);
//                    quality.setRegion(arr[1]);
                    quality.setKdRatio(Double.valueOf(arr[2].replace("%", "")));
//                    quality.setIsp(arr[2]);
                    quality.setErrorRatio(Double.valueOf(arr[3].replace("%", "")));
                    qualityMapper.insert(quality);

//                    System.out.println(quality);
                }

                line = br.readLine();
            }

            br.close();
            is.close();
            if (response != null)
                response.close();
        } catch (Exception e) {
            log.error("质量接口错误 e  = {} , url = {}", e, url);
        }

    }
}
