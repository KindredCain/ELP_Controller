package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Course;
import com.elp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private BaseService baseService;

    //增
    public void add(Course course){
        courseRepository.save(course);
    }
    //删
    public void delete(Course course){
        Course courseItem = courseRepository.findById(course.getObjectId());
        if(courseItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(courseRepository, courseItem);
        }
    }
    //改
    public void update(Course course){
        Course courseItem = courseRepository.findById(course.getObjectId());
        if(courseItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            courseRepository.save(course);
        }
    }
    //查询所有
    public List<Course> findAll(){
        List<Course> list = courseRepository.findAll();
        return  list;
    }
    //主key查询
    public  Course findById(String id){
        return courseRepository.findById(id);
    }
}
