package com.elp.handle;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by NWJ on 2017/6/18.
 */

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return Result.error(myException.getCode(), myException.getMessage());
        } else {
            System.out.println(e);
            return Result.error(ResultEnum.ERROR_UNKONW.getCode(), ResultEnum.ERROR_UNKONW.getMsg());
        }
    }
}
