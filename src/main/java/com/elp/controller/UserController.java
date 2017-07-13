package com.elp.controller;

import com.elp.exception.MyException;
import com.elp.model.Office;
import com.elp.model.ShowUser;
import com.elp.model.User;
import com.elp.service.CourseRecordService;
import com.elp.service.FileService;
import com.elp.service.UserService;
import com.elp.util.Result;
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
    @Autowired
    private FileService fileService;

    @PostMapping(value = "viewmyself.do")
    public Result viewMyself(@RequestParam("userId") String userId) {
        Object[] objects = userService.findByIdWithOffice(userId).get(0);
        Map returnMap = new HashMap();
        User user = (User) objects[0];
        Office office = (Office) objects[1];
        returnMap.put("user", user);
        returnMap.put("office", office);
        return Result.success(returnMap);
    }

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
}
