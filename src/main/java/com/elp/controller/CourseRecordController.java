package com.elp.controller;

import com.elp.model.CourseRecord;
import com.elp.service.CourseRecordService;
import com.elp.util.Result;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ASUS on 2017/7/11.
 */
@RestController
@CrossOrigin
public class CourseRecordController {
    @Autowired
    private CourseRecordService courseRecordService;

    @PostMapping(value = "/viewuseranalysis")
    public Result viewUserAnalysis(@RequestParam("userId") String userNum){
        List list = courseRecordService.findAllByUserNum(userNum);
        return Result.success();
    }
    @PostMapping(value = "/viewcourseanalysis")
    public Result viewCourseAnalysis(@RequestParam("courseId") String courseNum){
        List list = courseRecordService.findAllByCourseNum(courseNum);
        return Result.success();
    }

}
