package com.elp.controller;

import com.elp.model.Discuss;
import com.elp.model.User;
import com.elp.service.CourseService;
import com.elp.service.DiscussService;
import com.elp.service.UserService;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by NWJ on 2017/7/2.
 */

@RestController
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
    public String discussTest(){
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
        str += "find:"+discuss1.getDiscussContent();
        discuss1.setDiscussContent("test2");
        discuss1 = discussService.findById(id).get(0);
        str += "updata: "+discuss1.getDiscussContent();
        discussService.delDiscuss(id);

        return  str;
    }
    @PostMapping(value = "/testtables")
    public  String testtables(){
        List list = discussService.findDiscussTest();
        Map<String,String> map = (Map<String, String>) list.get(0);
        String str = map.get("userName")+":"+map.get("discussContent");
        return str;
    }
}
