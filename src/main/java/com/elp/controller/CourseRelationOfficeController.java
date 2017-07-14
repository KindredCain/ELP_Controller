package com.elp.controller;

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
    @PostMapping(value = "viewusersubject")
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
    //
    @PostMapping(value = "/addsubject")
    public Result addsubject(@RequestParam("courseId")String courseId,@RequestParam("subjectName") String subjectName,
                             @RequestParam("officeId")String officeId){
        CourseRelationOffice courseRelationOffice = new CourseRelationOffice();
        courseRelationOffice.setCourseNum(courseId);
        courseRelationOffice.setOfficeNum(officeId);
        courseRelationOffice.setSubjectName(subjectName);
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
