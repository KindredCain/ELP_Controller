package com.elp.controller;

import com.elp.model.Course;
import com.elp.model.CourseRelationOffice;
import com.elp.service.CourseRelationOfficeService;
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
public class CourseRelationOfficeController {
    @Autowired
    private CourseRelationOfficeService courseRelationOfficeService;

    //所有的课程职位中间表
    @PostMapping(value = "/viewallsubjects")
    public Result viewAllSubjects(){
        List list = courseRelationOfficeService.findAllGroupBySubjectName();
        List<Map> returnMap = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Map map = new HashMap();
            map.put("Subject",list.get(i));
            returnMap.add(map);
        }
        return Result.success(returnMap);
    }
    //查询用户的对应课程职位中间表
    @PostMapping(value = "/viewusersubject")
    public Result viewUserSubject(@RequestParam("userId") String userId){
        List list = courseRelationOfficeService.findByUserIdGroupBySubjectName(userId);
        List<Map> returnMap = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Map map = new HashMap();
            Object[] objects = (Object[]) list.get(i);
            map.put("Subject",objects[1]);
            returnMap.add(map);
        }
        return Result.success(returnMap);
    }

    //添加课程、职位中间关系
    @PostMapping(value = "/addsubject")
    public Result addsubject(CourseRelationOffice courseRelationOffice){
        courseRelationOfficeService.add(courseRelationOffice);
        return Result.success();
    }

    @PostMapping(value = "/delsubject")
    public Result delSubject(@RequestParam("subjectId") String subjectId){
        CourseRelationOffice courseRelationOffice = new CourseRelationOffice();
        courseRelationOffice.setObjectId(subjectId);
        courseRelationOfficeService.delete(courseRelationOffice);
        return Result.success();
    }

}
