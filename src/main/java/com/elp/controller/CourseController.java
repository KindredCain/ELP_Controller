package com.elp.controller;

import com.elp.model.Course;
import com.elp.model.CourseRecord;
import com.elp.service.CourseRecordService;
import com.elp.service.CourseService;
import com.elp.service.LessonService;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //模糊查询课程
    @PostMapping(value = "/searchcourses")
    public Result searchCourses(@RequestParam("userId") String userId, @RequestParam("courseName") String courseName) {
        courseName = "%" + courseName + "%";
        List<Course> courseList = courseService.findAllByUserIdAndLikeCourseName(userId, courseName);
        return Result.success(courseList);
    }

    //查看用户选中的课程
    @PostMapping(value = "/viewusercourse")
    public Result viewUserCourse(@RequestParam("userId") String userId, @RequestParam("courseNum") String courseNum) {
       List list = courseService.findLearndCourseAndRecodByUserIdAndCourseNum(userId, courseNum);
        if (list.size() != 0) {

        } else {
            CourseRecord tempCourseRecord = new CourseRecord();
            tempCourseRecord.setCourseComplete(0);
            tempCourseRecord.setCourseNum(courseNum);
            tempCourseRecord.setUserNum(userId);
            courseRecordService.add(tempCourseRecord);
            list = courseService.findLearndCourseAndRecodByUserIdAndCourseNum(userId,courseNum);
        }

        Object[] objects = (Object[]) list.get(0);
        Map returnMap = new HashMap();
        returnMap.put("Course",objects[0]);
        returnMap.put("CourseRecord",objects[1]);
        return Result.success(returnMap);
    }

    //查看用户的历史记录
    @PostMapping(value = "/viewlearndhistory")
    public Result viewLearndHistory(@RequestParam("userId") String userId) {
        List<Object[]> list = courseService.findAllLearndByUserId(userId);
        return Result.success(list);
    }

    //查看用户所有允许查看的课程
    @PostMapping(value = "/viewallcourse")
    public Result viewAllCourse(@RequestParam("userId") String userId) {
        List<Object[]> list = courseService.findAllByUserId(userId);
        return Result.success();
    }

    //查看用户推荐课程
    @PostMapping(value = "/viewrecommendcourse")
    public Result viewRecommendCourse(@RequestParam("userId") String userId) {

        return Result.success();
    }

    //添加课程
    @PostMapping(value = "/addcourse")
    public Result addCourse(@RequestParam("userId") String adminNum, @RequestParam("courseName") String courseName,
                            @RequestParam("coursePower") String coursePower, @RequestParam("courseSumLesson") String courseSumLesson,
                            @RequestParam("expectComplete") String expectComplete) {
        Course course = new Course();
        course.setAdminNum(adminNum);
        course.setCourseName(courseName);
        course.setCoursePower(Integer.valueOf(coursePower));
        course.setExpectComplete(expectComplete);
        course.setCourseSumLesson(Double.valueOf(courseSumLesson));
        //缺少课程info
        String path = "";
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        course.setCourseUrl(path);
        courseService.add(course);
        return Result.success();
    }

    //删除课程
    @PostMapping(value = "/delcourse")
    public Result delCourse(@RequestParam("courseId") String courseId) {
        Course course = new Course();
        course.setObjectId(courseId);
        courseService.delete(course);
        return Result.success();
    }

    //修改课程
    @PostMapping(value = "/editcourse")
    public Result updateCourse(@RequestParam("userId") String adminNum, @RequestParam("courseName") String courseName,
                               @RequestParam("coursePower") String coursePower, @RequestParam("courseSumLesson") String courseSumLesson,
                               @RequestParam("expectComplete") String expectComplete, @RequestParam("courseUrl") String courseUrl) {
        Course course = new Course();
        course.setAdminNum(adminNum);
        course.setCourseName(courseName);
        course.setCoursePower(Integer.valueOf(coursePower));
        course.setExpectComplete(expectComplete);
        course.setCourseSumLesson(Double.valueOf(courseSumLesson));
        //缺少课程info
        course.setCourseUrl(courseUrl);
        courseService.update(course);
        return Result.success();
    }
    @PostMapping(value = "/checkcourse")
    public Result checkCourse(@RequestParam("userId") String userId,@RequestParam("courseNum") String courseId){
        Course course = courseService.checkFindByUserIdAndCourseNum(userId,courseId);
        return Result.success(course);
    }
}
