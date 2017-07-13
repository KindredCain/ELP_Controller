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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by NWJ on 2017/7/13.
 */

@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private MsgService msgService;
    @Autowired
    private CourseRecordService courseRecordService;
    @Autowired
    private LessonRecordService lessonRecordService;
    @Autowired
    private FileService fileService;


    //课程操作Start
    //添加课程
    @PostMapping(value = "/addcourse")
    public Result addCourse(Course course) {
        courseService.add(course);
        return Result.success();
    }

    //删除课程
    @PostMapping(value = "/delcourse")
    public Result delCourse(Course course) {
        courseService.delete(course);
        return Result.success();
    }

    //修改课程
    @PostMapping(value = "/editcourse")
    public Result updateCourse(Course course) {
        courseService.update(course);
        //修改课程后发送消息
        List<CourseRecord> courseRecourdList = courseRecordService.findAllByCourseNum(course.getObjectId());
        for (int i = 0; i < courseRecourdList.size(); i++) {
            CourseRecord courseRecord = courseRecourdList.get(i);
            Msg msg = new Msg();
            msg.setCourseNum(course.getObjectId());
            msg.setMsgStats("unreaded");
            msg.setMsgType("课程修改");
            msg.setSendUser("Admin");
            msg.setRecUser(courseRecord.getUserNum());
            msg.setMsgContent("您学习的课程" + course.getCourseName() + "更新了新的内容");
            msgService.add(msg);
        }
        return Result.success();
    }
    //课程操作End

    //课时操作Start
    @PostMapping(value = "/addlesson")
    public Result addLesson (Lesson lesson){
        lessonService.add(lesson);
        return Result.success();
    }
    @PostMapping(value = "/dellesson")
    public Result delLesson (Lesson lesson){
        lessonService.delete(lesson);
        return Result.success();
    }
    @PostMapping(value = "/editlesson")
    public Result editLesson (Lesson lesson){
        lessonService.update(lesson);
        List<LessonRecord> lessonRecordList = lessonRecordService.findAllByLessonNum(lesson.getObjectId());
        for(int i = 0; i < lessonRecordList.size(); i++){
            LessonRecord lessonRecord = lessonRecordList.get(i);
            Msg msg = new Msg();
            msg.setCourseNum(lesson.getCourseNum());
            msg.setLessonNum(lesson.getObjectId());
            msg.setMsgStats("unreaded");
            msg.setMsgType("课时修改");
            msg.setSendUser("Admin");
            msg.setRecUser(lessonRecord.getUserNum());
            msg.setMsgContent("您学习的课时" + lesson.getLessonName() + "更新了新的内容");
            msgService.add(msg);
        }
        return Result.success();
    }
    @PostMapping(value = "/editlessonorder")
    public Result editLeddonOrder (@RequestParam("list")List<Map> list){
        List<Lesson> lessonPost = new ArrayList<>();
        int flag = 0;
        for(int i = 0; i < list.size(); i++){
            Map<String, List> map = list.get(i);
            for(Map.Entry<String, List> entry : map.entrySet()){
                List<String> lessonList = entry.getValue();
                for(int j = 0; j < lessonList.size(); j++){
                    flag++;
                    Lesson lesson = new Lesson();
                    lesson.setObjectId(lessonList.get(j));
                    lesson.setLessonName(entry.getKey());
                    lesson.setLessonOrder(flag);
                    lessonPost.add(lesson);
                }
            }
        }
        lessonService.updateLessonOrder(lessonPost);
        return Result.success();
    }
    //课时操作End

    //职位操作Start
    @PostMapping(value = "/addoffice")
    public Result addOffice (Office office){
        officeService.add(office);
        return Result.success();
    }
    @PostMapping(value = "/deloffice")
    public Result delOffice (Office office){
        officeService.delete(office);
        return Result.success();
    }
    @PostMapping(value = "/editoffic")
    public Result editOffice (Office office){
        officeService.update(office);
        return Result.success();
    }
    //职位操作End

    //用户操作Start
    @PostMapping(value = "/adduser")
    public Result addUser (User user){
        userService.add(user);
        return Result.success();
    }
    @PostMapping(value = "/deluser")
    public Result delUser (User user){
        userService.delete(user);
        return Result.success();
    }
    @PostMapping(value = "/adduserbyfile")
    public Result addUserByfile(@RequestParam("file")MultipartFile file){
        if (file != null){
            System.out.println("File Not NULL");
            String fileName = file.getOriginalFilename();
            List<Map<String,String>> list = fileService.viewExcelFile("xlsx",file);
            for (int i=0;i<list.size();i++){
                Map<String,String> tempMap = list.get(i);
                User user = new User();
                user.setLogId(tempMap.get("logId"));
                user.setPwd(tempMap.get("pwd"));
                user.setOfficeNum(tempMap.get("officeNum"));
                user.setUserType(tempMap.get("userType"));
                user.setUserName(tempMap.get("userName"));
                userService.add(user);
            }
        } else {
            System.out.println("File is NULL");
        }
        return Result.success();
    }
    @PostMapping(value = "/edituserpower")
    public Result editUserPower(User user){
        userService.updatePower(user);
        return Result.success();
    }
    @PostMapping(value = "/edituserpwd")
    public Result editUserPwd(User user){
        userService.updatePwd(user);
        return Result.success();
    }
    //用户操作End
}
