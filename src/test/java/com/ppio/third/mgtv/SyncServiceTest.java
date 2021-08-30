package com.ppio.third.mgtv;


import com.ppio.third.mgtv.bean.AccessUrlBean;
import com.ppio.third.mgtv.entity.TMgtvAccesslogMachine;
import com.ppio.third.mgtv.mapper.TMgtvAccesslogMachineMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sound.midi.Soundbank;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SyncServiceTest {

    @Autowired
    SyncService syncService;

    @Autowired
    TMgtvAccesslogMachineMapper accesslogMachinesMapper;


    @Test
    public void t() {
        TMgtvAccesslogMachine machine = accesslogMachinesMapper.selectByPrimaryKey(1L);
        List<AccessUrlBean> accessUrlBeans = syncService.getAccessUrlBeans(machine);
        for (AccessUrlBean bean : accessUrlBeans) {
            System.out.println(bean);
        }
    }


    @Test
    public void syncTest() {
        List<TMgtvAccesslogMachine> onlineMachines = syncService.getOnlineMachines();
        for (TMgtvAccesslogMachine machine : onlineMachines) {
            List<AccessUrlBean> accessUrlBeans = syncService.getAccessUrlBeans(machine);
            if (accessUrlBeans == null || accessUrlBeans.size() == 0)
                continue;
            for (AccessUrlBean bean : accessUrlBeans) {
                syncService.sync(machine.getMachineId(), bean);
            }
        }
    }


    //maintainMachines
    @Test
    public void maintainMachinesTest() {
        syncService.maintainMachines();
    }

    //getOnlineMachines
    @Test
    public void getOnlineMachines() {
        for (TMgtvAccesslogMachine machine : syncService.getOnlineMachines()) {
            System.out.println(machine);
        }
    }
}
