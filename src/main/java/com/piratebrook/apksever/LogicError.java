package com.piratebrook.apksever;

public enum LogicError {

    NoData(101, "无数据");

    int code;

    String msg;

    LogicError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
