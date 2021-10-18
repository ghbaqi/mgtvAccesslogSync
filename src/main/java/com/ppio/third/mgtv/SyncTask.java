package com.ppio.third.mgtv;

import com.ppio.third.mgtv.bean.AccessUrlBean;
import com.ppio.third.mgtv.entity.TMgtvAccesslogMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executor;

/**
 *
 */
@Slf4j
@Component
public class SyncTask {

    @Autowired
    private Executor downloadthreadPool;

    @Autowired
    private SyncService syncService;

    // 1. 定时维护 芒果在线机器列表
    @Scheduled(cron = "0 0/10 * * * ? ")
    public void maintainOnlineMachine() {
        log.info("SyncTask maintainOnlineMachine");
        syncService.maintainMachines();
    }


    /**
     *
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
//    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void sync() {
        log.info("SyncTask sync");
        List<TMgtvAccesslogMachine> onlineMachines = syncService.getOnlineMachines();
        for (TMgtvAccesslogMachine machine : onlineMachines) {
            downloadthreadPool.execute(() -> {
                List<AccessUrlBean> urlBeans = syncService.getAccessUrlBeans(machine);
                if (urlBeans != null && urlBeans.size() > 0) {
                    for (AccessUrlBean urlBean : urlBeans) {
                        syncService.sync(machine.getMachineId(), urlBean);
                    }
                }
            });
        }
    }


    // 获取质量接口数据
    //-- 江苏移动质量数据地址:http://39.96.63.155/ppio-cmnet-jiangsu/quality.txt.latest
    //-- 芒果质量 接口数据 。
    //-- 湖南移动质量数据地址:http://39.96.63.155/ppio-cmnet-hunan/quality.txt.latest
    //-- 河南联通质量数据地址:http://39.96.63.155/ppio-cnc-henan/quality.txt.latest
    @Scheduled(cron = "0 0/5 * * * ? ")
    public void getQualityData() {
        log.info("SyncTask getQualityData");
        syncService.getQualityData("http://39.96.63.155/ppio-cmnet-jiangsu/quality.txt.latest");
        syncService.getQualityData("http://39.96.63.155/ppio-cmnet-hunan/quality.txt.latest");
        syncService.getQualityData("http://39.96.63.155/ppio-cnc-henan/quality.txt.latest");
    }

}
