package com.elp.controller;

import com.elp.model.*;
import com.elp.service.DiscussService;
import com.elp.service.MsgService;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/7/12.
 */
@RestController
@CrossOrigin
public class DiscussController {
    @Autowired
    private DiscussService discussService;
    @Autowired
    private MsgService msgService;

    //查看课程下的讨论
    @PostMapping(value = "viewlessondiscuss")
    public Result viewLessonDiscuss(@RequestParam("lessonId") String lessonId){
        List<Object[]> list = discussService.findAllByLessonId(lessonId);
        List<Map> returnMap = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Object[] objects = list.get(i);
            Map tempMap = new HashMap();
            tempMap.put("Discuss",objects[0]);
            tempMap.put("User",objects[1]);
            returnMap.add(tempMap);
        }
        return Result.success(returnMap);
    }

    //发表讨论
    @PostMapping(value = "/adddiscuss")
    public Result writeDiscuss(Discuss discuss){
        discussService.add(discuss);
        if(discuss.getReDiscussNum() != "" && discuss.getReDiscussNum() != null){
            Object[] objects = discussService.findByIdWithLessonAndCourse(discuss.getReDiscussNum()).get(0);
            Discuss tempDiscuss = (Discuss) objects[0];
            User tempUser = (User) objects[1];
            Lesson tempLesson = (Lesson) objects[2];
            Course tempCourse = (Course) objects[3];
            Msg msg = new Msg();
            msg.setRecUser(tempUser.getObjectId());
            msg.setSendUser(discuss.getTalkUserNum());
            msg.setMsgType("讨论回复");
            msg.setCourseNum(tempCourse.getObjectId());
            msg.setLessonNum(tempLesson.getObjectId());
            msg.setMsgStats("unreaded");
            msg.setMsgContent("您在"+tempCourse.getCourseName()+"课程下的"+tempLesson.getLessonName()+"的讨论有了新的回复");
            msgService.add(msg);
        }
        return Result.success();
    }

    @PostMapping(value = "/deldiscuss")
    public Result delDiscuss(@RequestParam("discussId") String discussId){
        Discuss discuss = new Discuss();
        discuss.setObjectId(discussId);
        discussService.delete(discuss);
        return Result.success();
    }
}
