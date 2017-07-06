package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Course;
import com.elp.model.User;
import com.elp.repository.CourseRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class CourseService {
    @Autowired
    private CourseRespositroy courseRespositroy;
    //增
    public void add(Course course){
        courseRespositroy.save(course);
    }
    //查找所有
    public List<Course> allCourse(){
        List<Course> list = courseRespositroy.findAll();
        return  list;
    }
    //精确查找
    public  List<Course> findById(String id){
        return courseRespositroy.findById(id);
    }

    //修改
    public void updateCourse(Course course){
        Course courseOne = courseRespositroy.findOne(course.getObjectId());
        if(courseOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            courseRespositroy.save(course);
        }
    }
    //删除用户
    public void delCourse(String id){
        Course courseOne = courseRespositroy.findOne(id);
        if(courseOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            courseOne.setDelTime(time);
            courseRespositroy.save(courseOne);
        }
    }
}
