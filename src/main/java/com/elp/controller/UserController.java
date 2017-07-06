package com.elp.controller;

import com.elp.repository.UserRespositroy;
import com.elp.service.UserService;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by ASUS on 2017/7/6.
 */
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
//    @PostMapping(value = "/login")
//    public Result loginConfirm(@RequestParam("logId") String logId,@RequestParam("pwd") String pwd) {
//        Map<String,String> map = userService.checkLogin(logId,pwd);
//        return Result.success(map);
//    }
}
