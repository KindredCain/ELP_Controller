package com.elp.controller;

import com.elp.exception.MyException;
import com.elp.model.Office;
import com.elp.model.ShowUser;
import com.elp.model.User;
import com.elp.service.CourseRecordService;
import com.elp.service.FileService;
import com.elp.service.UserService;
import com.elp.util.Result;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/7/7.
 */

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    //用户查看自己
    @PostMapping(value = "/viewmyself")
    public Result viewMyself(@RequestParam("userId") String userId) {
        Object[] objects = userService.findByIdWithOffice(userId).get(0);
        Map returnMap = new HashMap();
        User user = (User) objects[0];
        Office office = (Office) objects[1];
        returnMap.put("user", user);
        returnMap.put("office", office);
        return Result.success(returnMap);
    }

    //用户查看其它用户信息
    @PostMapping(value = "/viewotheruser")
    public Result viewOtherUser(@RequestParam("logId") String logId) {
        ShowUser showUser = userService.findByLogIdFromOther(logId);
        Map returnMap = new HashMap();
        List<Map> templist = new ArrayList<>();
        Map tempMap = new HashMap();
        tempMap.put("showUser", showUser);
        returnMap.put("showUserList", templist);
        return Result.success(templist);
    }

    //选出学霸
    @PostMapping(value = "/viewmaxshowusers")
    public Result viewMaxShowUsers() {
        List<ShowUser> showUserList = userService.findMax();
        Map returnMap = new HashMap();
        List<Map> mapList = new ArrayList<>();
        for (int i = 0; i < showUserList.size(); i++) {
            Map tempMap = new HashMap();
            tempMap.put("showUser", showUserList.get(i));
            mapList.add(tempMap);
        }
        returnMap.put("shouUserList", mapList);
        return Result.success(returnMap);
    }

    //修改用户信息，可修改用户名、图片地址和职位
    @PostMapping(value = "/changeuserinfo")
    public Result changeUserInfo(User user){
        userService.updateInfo(user);
        return Result.success();
    }

    //修改用户自己的密码
    @PostMapping(value = "/changemypwd")
    public Result changeMyPwd(User user,@RequestParam("newPwd") String newPwd){
        userService.updateMyPwd(user,newPwd);
        return Result.success();
    }
}
