package com.elp.enums;

/**
 * Created by NWJ on 2017/6/18.
 */

public enum ResultEnum {
    ERROR_UNKONW(-1, "未知错误"),
    SUCCESS(200, "成功"),
    ERROR_100(100, "没有权限"),
    ERROR_101(101, "没有数据"),
    ERROR_102(102, "登录失败"),
    ERROR_103(103, "文件操作失败"),
    ERROR_104(104, "文件读取失败"),
    ERROR_105(105,"密码错误"),;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
