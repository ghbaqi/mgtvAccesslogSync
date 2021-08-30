package com.ppio.third.mgtv;

import lombok.Data;

public enum UploadStatusEnum {

    UploadLoading(0, "进行中"),
    Failure(-1, "失败"),
    Success(1, "成功");


    private int status;
    private String desc;

    private UploadStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
