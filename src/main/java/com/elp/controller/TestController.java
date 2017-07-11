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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NWJ on 2017/7/2.
 */

@RestController
@CrossOrigin
public class TestController {
    @Autowired
    UserService userService;
    @Autowired
    LessonService lessonService;

    @PostMapping(value = "/posttest")
    public Result postTest(String name) {
        return Result.success(name);
    }

    @GetMapping(value = "/gettest/{name}")
    public Result getTest(@PathVariable("name") String name) {
        return Result.success(name);
    }

    @PostMapping(value = "/maxuser")
    public Result maxUser() {
        return Result.success(userService.findMax());
    }

    @PostMapping(value = "/findAllWithLessonRecord")
    public Result findAllWithLessonRecord(){
        List<Object[]> list = lessonService.findAllWithLessonRecord("2");
        List<Map> resultList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            Object[] objects = list.get(i);
            Map resultMap = new HashMap();
            resultMap.put("Lesson", objects[0]);
            resultMap.put("LessonRecord", objects[1]);
            resultMap.put("Course", objects[2]);
            resultList.add(resultMap);
        }
        return Result.success(resultList);
    }

    @PostMapping(value = "/findByIdWithLessonRecord")
    public Result findByIdWithLessonRecord(){
        return Result.success(lessonService.findByIdWithLessonRecord("1", "2"));
    }

    @PostMapping(value = "/findByCourseNumWithLessonRecord")
    public Result findByCourseNumWithLessonRecord(){
        return Result.success(lessonService.findByCourseNumWithLessonRecord("1", "2"));
    }
}
