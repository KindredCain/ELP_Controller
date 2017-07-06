package com.elp.controller;

import com.elp.model.Discuss;
import com.elp.model.User;
import com.elp.service.CourseService;
import com.elp.service.DiscussService;
import com.elp.service.UserService;
import com.elp.util.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by NWJ on 2017/7/2.
 */

@RestController
@CrossOrigin
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private DiscussService discussService;


    @PostMapping(value = "/posttest")
    public Result postTest(String name) {
        return Result.success(name);
    }

    @GetMapping(value = "/gettest/{name}")
    public Result getTest(@PathVariable("name") String name) {
        return Result.success(name);
    }

    @PostMapping(value = "/discuss")
    public String discussTest() {
        User user = new User();
        user = userService.allUser().get(0);
        String str = "";
        Discuss discuss = new Discuss();
        discuss.setDiscussContent("test");
        discuss.setLessonNum("1");
        discuss.setTalkUserNum(user.getObjectId());
        discuss.setDiscussType("normal");
        discussService.add(discuss);
        Discuss discuss1 = discussService.allDiscuss().get(0);
        String id = discuss1.getObjectId();
        str += "find:" + discuss1.getDiscussContent();
        discuss1.setDiscussContent("test2");
        discuss1 = discussService.findById(id).get(0);
        str += "updata: " + discuss1.getDiscussContent();
        discussService.delDiscuss(id);

        return str;
    }

    @PostMapping(value = "/createone")
    public String createOne() {
        User user = new User();
        user.setLogId("test");
        user.setPwd("test");
        user.setOfficeNum("1");
        user.setUserType("user");
        user.setUserName("zzz");
        userService.add(user);
        return user.getUserName();
    }

    @PostMapping(value = "/relation")
    public String findRelation() {
//        List list = relationService.findAll();
//        Relation relation = (Relation) list.get(0);
        String str = "";
        return str;
    }

    @PostMapping(value = "/testtables")
    public String testtables() {
//        List list = discussService.findDiscussTest();
        List list = discussService.findDiscussTest0();
        Map<String, String> map = (Map<String, String>) list.get(0);
        String str = map.get("userName") + ":" + map.get("discussContent");
        return str;
    }

    @PostMapping(value = "/testtables2")
    public Result testtables2() {
        List list = discussService.findDiscussTest();
        Map<String, String> map = (Map<String, String>) list.get(0);
//        String str = map.get("userName") + ":" + map.get("discussContent");
        return Result.success(map);
    }

    @PostMapping(value = "/testsql")
    public String testsql() {
        List list = discussService.findSqlTest();
        Discuss discuss = (Discuss) list.get(0);
        String str = discuss.getDiscussContent();
        return str;
    }

    @PostMapping(value = "/discussnum")
    public Result discussnum() {
        BigInteger num = discussService.findDiscussNum();
        String str = String.valueOf(num);
        return Result.success(str);
    }

    @PostMapping(value = "/userpage")
    public Result userPage() {
        List list = userService.allPageUser();
//        for(int i=0;i<page.getSize();i++){
//            Map<String,String> map = page.get
//        }
        return Result.success(list);
    }
}
