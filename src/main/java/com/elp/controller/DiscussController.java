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

    @PostMapping(value = "/writediscuss")
    public Result writeDiscuss(@RequestParam("discussContent") String discussContent,@RequestParam("lessonNum") String lessonNum,
                               @RequestParam("reDiscussNum") String reDiscussNum,@RequestParam("talkUserNum") String talkUserNum){
        Discuss discuss = new Discuss();
        discuss.setDiscussContent(discussContent);
        discuss.setTalkUserNum(talkUserNum);
        discuss.setLessonNum(lessonNum);
        discuss.setReDiscussNum(reDiscussNum);
        discussService.add(discuss);
        if(reDiscussNum != "" && reDiscussNum != null){
            Object[] objects = discussService.findAllById(reDiscussNum).get(0);
            Discuss tempDiscuss = (Discuss) objects[0];
            User tempUser = (User) objects[1];
            Lesson tempLesson = (Lesson) objects[2];
            Course tempCourse = (Course) objects[3];
            Msg msg = new Msg();
            msg.setRecUser(tempUser.getObjectId());
            msg.setSendUser(talkUserNum);
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
