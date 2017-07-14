package com.elp.controller;

import com.elp.model.*;
import com.elp.service.*;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ASUS on 2017/7/9.
 */
@RestController
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private CourseRecordService courseRecordService;
    @Autowired
    private PagerankService pagerankService;
    @Autowired
    private FileService fileService;
    @Autowired
    private MsgService msgService;

    //模糊查询课程
    @PostMapping(value = "/searchcourses")
    public Result searchCourses(@RequestParam("userId") String userId, @RequestParam("courseName") String courseName) {
        courseName = "%" + courseName + "%";
        List<Course> courseList = courseService.findAllByUserIdAndLikeCourseName(userId, courseName);
        return Result.success(courseList);
    }
    //查看用户选中的课程
    @PostMapping(value = "/viewusercourse")
    public Result viewUserCourse(@RequestParam("userId") String userId, @RequestParam("courseNum") String courseNum) throws ParseException {
        List list = courseService.findLearndCourseAndRecodByUserIdAndCourseNum(userId, courseNum);
        if (list.size() != 0) {

        } else {
            CourseRecord tempCourseRecord = new CourseRecord();
            tempCourseRecord.setCourseComplete(0);
            tempCourseRecord.setCourseNum(courseNum);
            tempCourseRecord.setUserNum(userId);
            courseRecordService.add(tempCourseRecord);
            list = courseService.findLearndCourseAndRecodByUserIdAndCourseNum(userId, courseNum);
        }
        Object[] objects = (Object[]) list.get(0);
        Map returnMap = new HashMap();
        returnMap.put("Course", objects[0]);
        returnMap.put("CourseRecord", objects[1]);
        //根据课程查找对应的课时和课时记录存疑需要另外测试
        List<Object[]> lessonList = lessonService.findByCourseNumWithLessonRecord(courseNum,userId);
        List<Map> tempMapList = new ArrayList<>();
        for(int i=0;i<lessonList.size();i++){
            Object[] tempobject = lessonList.get(i);

            Lesson tempLesson = new Lesson((String)tempobject[0],(Date)tempobject[1],(Date)tempobject[2], (Date)tempobject[3],
                    (String)tempobject[4],(String)tempobject[5],(String)tempobject[6],(String)tempobject[7],(String)tempobject[8],(double)tempobject[9]);
            LessonRecord tempLessonRecord = new LessonRecord((String)tempobject[10],(Date)tempobject[11],(Date)tempobject[12],(Date)tempobject[13],(String)tempobject[14],(String)tempobject[15],(String)tempobject[16]);
            Course tempCourse = new Course((String)tempobject[17],(Date)tempobject[18],(Date)tempobject[19], (Date)tempobject[20],(String)tempobject[21],(String)tempobject[22],(String)tempobject[23],(double)tempobject[24],(String)tempobject[25],(int) tempobject[26],(String)tempobject[27],(String)tempobject[28]);
            Map tempMap = new HashMap();
            tempMap.put("lesson",tempLesson);
            tempMap.put("lessonRecord",tempLessonRecord);
            tempMap.put("Course",tempCourse);
            tempMapList.add(tempMap);
        }
        returnMap.put("LessonList",tempMapList);
        return Result.success(returnMap);
    }
    //查看用户的历史记录
    @PostMapping(value = "/viewlearndhistory")
    public Result viewLearndHistory(@RequestParam("userId") String userId) {
        List<Object[]> list = courseService.findAllLearndByUserId(userId);
        List<Map> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map map = new HashMap();
            Object[] objects = list.get(i);
            map.put("Course",objects[0]);
            map.put("Lesson",objects[1]);
            map.put("LessonRecord",objects[2]);
            returnList.add(map);
        }
        return Result.success(returnList);
    }
    //查看用户所有允许查看的课程
    @PostMapping(value = "/viewallcourse")
    public Result viewAllCourse(@RequestParam("userId") String userId) {
        List<Object[]> list = courseService.findAllByUserId(userId);
        List<Map> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map map = new HashMap();
            Object[] objects = list.get(i);
            map.put("Course",objects[0]);
            map.put("Subject",objects[1]);
            map.put("LessonRecord",objects[2]);
            returnList.add(map);
        }
        System.out.println(returnList.size());
        return Result.success(returnList);
    }
    //查看用户推荐课程
    @PostMapping(value = "/viewrecommendcourse")
    public Result viewRecommendCourse(@RequestParam("userId") String userId) {
        List<Map.Entry<String, Double>> list = pagerankService.mainRank(userId);
        List<Map> returnMap = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Course course = courseService.checkFindByUserIdAndCourseNum(userId,list.get(i).getKey());
            if (course != null){
                Map map = new HashMap();
                map.put("course",course);
                returnMap.add(map);
            }
        }
        return Result.success(returnMap);
    }
    //根据方向名查找课程
    @PostMapping(value = "/viewbysubjectname")
    public Result viewBySubjectName(@RequestParam("subjectName") String subjectName,@RequestParam("userId") String userId){
        List<Object[]> list = courseService.findAllBySubjectName(subjectName,userId);
        System.out.println(list.size());
        return Result.success(list);
    }


}
