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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i=0;i<lessonList.size();i++){
            Object[] tempobject = lessonList.get(i);

            Lesson tempLesson = new Lesson((String)tempobject[0],(Date)tempobject[1],(Date)tempobject[2], (Date)tempobject[3],
                    (String)tempobject[4],(String)tempobject[5],(String)tempobject[6],(String)tempobject[7],(String)tempobject[8],(double)tempobject[9]);
            LessonRecord tempLessonRecord = new LessonRecord((String)tempobject[10],(Date)tempobject[11],(Date)tempobject[12],(Date)tempobject[13],(String)tempobject[14],(String)tempobject[15],(String)tempobject[16]);
            //integer类型难以处理
//            Course tempCourse = new Course((String)tempobject[17],(Date)tempobject[18],(Date)tempobject[19], (Date)tempobject[20],(String)tempobject[21],(String)tempobject[22],(String)tempobject[23],(double)tempobject[24],(String)tempobject[25],Integer.valueOf(tempobject[26].toString()).intValue(),(String)tempobject[27],(String)tempobject[28]);
//            Course tempCourse = new Course();
//            int temp = Integer.valueOf(tempobject[26].toString()).intValue();
//            tempCourse.setCoursePower(temp);
//            tempCourse.setCourseSumLesson((double) tempobject[24]);
            Map tempMap = new HashMap();
            tempMap.put("lesson",tempLesson);
            tempMap.put("lessonRecord",tempLessonRecord);
//            tempMap.put("Course",tempCourse);
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
    //添加课程
    @PostMapping(value = "/addcourse")
    public Result addCourse(@RequestParam("userId") String adminNum, @RequestParam("courseName") String courseName,
                            @RequestParam("coursePower") String coursePower, @RequestParam("courseSumLesson") String courseSumLesson,
                            @RequestParam("expectComplete") String expectComplete,@RequestParam("courseInfo") String courseInfo,
                            @RequestParam("pic")String picurl) {
        Course course = new Course();
        course.setAdminNum(adminNum);
        course.setCourseName(courseName);
        course.setCoursePower(Integer.valueOf(coursePower));
        course.setExpectComplete(expectComplete);
        course.setCourseSumLesson(Double.valueOf(courseSumLesson));
        course.setCourseInfo(courseInfo);
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        //采用相对路径会发生目录从项目目录下转变为C：下的问题
        String path = "src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"course"+ File.separator+adminNum+File.separator+dateFormater.format(date)+File.separator+courseName;
        File folder = new File(path);
        while (!folder.exists()) {
            folder.mkdirs();
        }
        course.setCourseUrl(path);
        course.setCoursePicUrl(picurl);
//        fileService.uploadFile(pic.getOriginalFilename(),path,pic);
        courseService.add(course);
        return Result.success();
    }

    //删除课程
    @PostMapping(value = "/delcourse")
    public Result delCourse(@RequestParam("courseId") String courseId) {
        Course course = courseService.findById(courseId);
        if (course != null) {
            course.setObjectId(courseId);
            courseService.delete(course);
            return Result.success();
        }
        return Result.success();
    }

    //修改课程
    @PostMapping(value = "/editcourse")
    public Result updateCourse(@RequestParam("courseId")String courseId,@RequestParam("userId") String adminNum, @RequestParam("courseName") String courseName,
                               @RequestParam("courseInfo")String courseInfo,@RequestParam("coursePower") String coursePower, @RequestParam("courseSumLesson") String courseSumLesson,
                               @RequestParam("expectComplete") String expectComplete, @RequestParam("picUrl") String picUrl) {
        Course course = courseService.findById(courseId);
        if (course != null && course.getAdminNum().equals(adminNum)){
            course.setCourseName(courseName);
            course.setCoursePower(Integer.valueOf(coursePower));
            course.setExpectComplete(expectComplete);
            course.setCourseSumLesson(Double.valueOf(courseSumLesson));
            course.setCourseInfo(courseInfo);
            course.setCoursePicUrl(picUrl);
            courseService.update(course);
            //修改课程后发送消息
            List<CourseRecord> courseRecourdList = courseRecordService.findAllByCourseNum(course.getObjectId());
            for (int i=0;i<courseRecourdList.size();i++){
                CourseRecord courseRecord = courseRecourdList.get(i);
                Msg msg = new Msg();
                msg.setCourseNum(course.getObjectId());
                msg.setMsgStats("unreaded");
                msg.setMsgType("课程修改");//course需要根据课程id返回对应所有的内容
                msg.setSendUser(adminNum);
                msg.setRecUser(courseRecord.getUserNum());
                msg.setMsgContent("您学习的课程"+course.getCourseName()+"更新了新的内容");
                msgService.add(msg);
            }
        }
        return Result.success();
    }

}
