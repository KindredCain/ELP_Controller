package com.elp.controller;

import com.elp.model.Course;
import com.elp.model.CourseRecord;
import com.elp.service.CourseRecordService;
import com.elp.util.Result;
import org.hibernate.annotations.Parameter;
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
 * Created by ASUS on 2017/7/11.
 */
@RestController
@CrossOrigin
public class CourseRecordController {
    @Autowired
    private CourseRecordService courseRecordService;

    //显示用户统计数据
    @PostMapping(value = "/viewuseranalysis")
    public Result viewUserAnalysis(@RequestParam("userId") String userNum){
        List list = courseRecordService.findAllByUserNum(userNum);
        Map tempMap = new HashMap();
        tempMap.put("Nums",list.size());
        tempMap.put("courseRecord",list);
        return Result.success(tempMap);
    }
    //显示课程统计数据
    @PostMapping(value = "/viewcourseanalysis")
    public Result viewCourseAnalysis(@RequestParam("courseId") String courseNum){
        List list = courseRecordService.findAllByCourseNum(courseNum);
        Map tempMap = new HashMap();
        tempMap.put("Nums",list.size());
        tempMap.put("courseRecord",list);
        return Result.success(tempMap);
    }
    //更新课程记录
    @PostMapping(value = "/updatecourserecord")
    public Result updateCourseRecord(@RequestParam("courseComplete") String courseComplete,@RequestParam("courseRecordId") String courseRecordId){
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.setObjectId(courseRecordId);
        courseRecord.setCourseComplete(Double.valueOf(courseComplete));
        courseRecordService.update(courseRecord);
        return Result.success();
    }

}
