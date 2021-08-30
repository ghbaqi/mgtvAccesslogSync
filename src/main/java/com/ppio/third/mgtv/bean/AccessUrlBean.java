package com.ppio.third.mgtv.bean;


import lombok.Data;

/**
 * [
 * {
 * "name": "access.log.202108182200.gz",
 * "size": 557812305,
 * "url": "http://221.229.163.80:20000/download.do?f=access.log.202108182200.gz",
 * "crc32": "bd50a1f7"
 * },
 * {
 * "name": "access.log.202108200600.gz",
 * "size": 21153664,
 * "url": "http://221.229.163.80:20000/download.do?f=access.log.202108200600.gz",
 * "crc32": "46e11a01"
 * },
 * {
 * "name": "access.log.202108200700.gz",
 * "size": 20375459,
 * "url": "http://221.229.163.80:20000/download.do?f=access.log.202108200700.gz",
 * "crc32": "2610ca74"
 * }
 * <p>
 * ]
 */

@Data
public class AccessUrlBean {


    public AccessUrlBean() {
    }

    public AccessUrlBean(String name, long size, String url, String crc32) {
        this.name = name;
        this.size = size;
        this.url = url;
        this.crc32 = crc32;
    }

    private String name;
    private long size;
    private String url;
    private String crc32;

}
