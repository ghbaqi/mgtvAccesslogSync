package com.ppio.third.mgtv;

import com.ppio.third.mgtv.entity.TMgtvAccesslogItems;
import com.ppio.third.mgtv.entity.TMgtvAccesslogMachine;
import com.ppio.third.mgtv.mapper.TMgtvAccesslogItemsMapper;
import com.ppio.third.mgtv.mapper.TMgtvAccesslogMachineMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DaoTest {

    @Autowired
    TMgtvAccesslogMachineMapper machineMapper;


    @Autowired
    private TMgtvAccesslogItemsMapper accesslogItemsMapper;

    @Test
    public void t() {

        TMgtvAccesslogItems existlogItem = new TMgtvAccesslogItems();
        existlogItem.setMachineId("m002");
        existlogItem.setFileName("urlBean.getName()");
        existlogItem.setUrl("urlBean.getUrl()");
        existlogItem.setStatus(UploadStatusEnum.UploadLoading.getStatus());
        System.out.println(accesslogItemsMapper.insert(existlogItem));
        System.out.println(existlogItem);
    }
}
