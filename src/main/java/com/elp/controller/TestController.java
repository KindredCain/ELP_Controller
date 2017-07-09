package com.elp.controller;

import com.elp.model.Course;
import com.elp.model.Office;
import com.elp.model.User;
import com.elp.repository.PagerankDao;
import com.elp.service.*;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private PagerankDao pagerankDao;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRecordService courseRecordService;
    @Autowired
    private CourseRelationOfficeService courseRelationOfficeService;
    @Autowired
    private PagerankService pagerankService;
    @Autowired
    private UserService userService;

    @PostMapping(value = "/posttest")
    public Result postTest(String name) {
        return Result.success(name);
    }

    @GetMapping(value = "/gettest/{name}")
    public Result getTest(@PathVariable("name") String name) {
        return Result.success(name);
    }

    @PostMapping(value = "/testrelation")
    public Result testrelation(){
        List<Object[]> list = pagerankDao.findPagerank();
        return  Result.success(list);
    }
    @PostMapping(value = "addtestvlaue")
    public Result addTestValue(){
//        officeService.addOfficesTest();
//        courseService.addCoursesTest();
        List<Office> officesList = officeService.findAll();
        List<User> userList = userService.findAll();
        List<Course> coursesList = courseService.findAll();
//        courseRelationOfficeService.addCourseRelationOfficesTest(officesList,coursesList);
        courseRecordService.addCourseRecordsTest(userList,coursesList);
        return Result.success();
    }
    @PostMapping(value = "/testrank")
    public Result testrank(){
//        officeService.addOfficesTest();
//        courseService.addCoursesTest();
//        courseRecordService.addCourseRecordsTest();
//        courseRelationOfficeService.addCourseRelationOfficesTest();
        List<Map.Entry<String, Double>> list = pagerankService.mainRank("402805815d1b4722015d1b475bfe0001");
        List<Course> returnList = new ArrayList<Course>();
        System.out.println(list.size());
        for (int i=0;i<list.size();i++){
            Map.Entry<String,Double> tempMap = list.get(i);
            System.out.println("i: "+tempMap.getKey());
            Course course = courseService.findById(tempMap.getKey());
            if (course != null){
                returnList.add(course);
            }
        }
        return Result.success(returnList);
    }

}
