package com.zhengcheng.mall.bbs.common.apiresult;

import java.io.Serializable;

public abstract class BaseSubResult implements Serializable {
    private int    subCode;
    private String subMsg;

    public int getSubCode() {
        return this.subCode;
    }

    public void setSubCode(int subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return this.subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public BaseSubResult(int subCode, String subMsg) {
        this.subCode = subCode;
        this.subMsg = subMsg;
    }

    public String toString() {
        return "subCode=" + this.subCode + "," + "subMsg" + "=" + this.subMsg;
    }
}
