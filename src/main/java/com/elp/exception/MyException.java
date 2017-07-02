package com.elp.exception;

import com.elp.enums.ResultEnum;

/**
 * Created by NWJ on 2017/6/18.
 */

public class MyException extends RuntimeException {

    private int code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
