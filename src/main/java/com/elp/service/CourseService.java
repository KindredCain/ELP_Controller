package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Course;
import com.elp.repository.CourseRepository;
import com.elp.repository.LessonRecordRepository;
import com.elp.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
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
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private LessonRecordRepository lessonRecordRepository;

    //增
    public void add(Course course) {
        courseRepository.save(course);
    }

    //删 事务注释
    @Transactional
    public void delete(Course course) {
        Course courseItem = courseRepository.findById(course.getObjectId());
        if (courseItem == null) {
            throw new MyException(ResultEnum.ERROR_101);
        } else {
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            System.out.println("delete lesson");
            lessonRepository.deleteByCourseNum(courseItem.getObjectId(),timestamp);
            System.out.println("delete lesson success");
            lessonRecordRepository.deleteByCourseNum(courseItem.getObjectId(),timestamp);
            System.out.println("delete lessonRecord success");
            baseService.delete(courseRepository, courseItem);
        }
    }

    //改
    public void update(Course course) {
        Course courseItem = courseRepository.findById(course.getObjectId());
        if (courseItem == null) {
            throw new MyException(ResultEnum.ERROR_101);
        } else {
            courseRepository.save(course);
        }
    }

    //查询所有
    public List<Course> findAll() {
        List<Course> list = courseRepository.findAll();
        return list;
    }

    //根据id和课程名模糊查询课程
    public List<Course> findAllByUserIdAndLikeCourseName(String userId, String courseName) {
        List<Course> list = courseRepository.findByCourseNameLike(userId, courseName);
        return list;
    }

    //根据用户id和课程id查询已学课程
    public List<Object[]> findLearndCourseAndRecodByUserIdAndCourseNum(String userId, String courseNum) {
        return courseRepository.findByObjectIdAndCourseNum(userId, courseNum);
    }

    //查找用户学过的课程，并根据最后观看时间倒序
    public List<Object[]> findAllLearndByUserId(String userId) {
        List<Object[]> list = courseRepository.findByUserNumOrderByUpdateTime(userId);
        return list;
    }

    //验证对应的用户是否可以查看对应课程
    public Course checkFindByUserIdAndCourseNum(String userId, String courseNum) {
        Course course = courseRepository.findByCourseIdAndObjectId(userId, courseNum);
        return course;
    }

    //根据用户id查找所有用户可看的课程
    public List<Object[]> findAllByUserId(String userId) {
        List<Object[]> list = courseRepository.findAllByUserId(userId);
        return list;
    }

    //主key查询
    public Course findById(String id) {
        return courseRepository.findById(id);
    }

    //查找最新的课程
    public List<Course> findAllOrderByCreatTime() {
        List<Course> list = courseRepository.findAllOrderByCreatTime();
        return list;
    }
    //根据用户id查找用户可看的最新课程
    public List<Course> findAllByUserIdOrderByCreatTime(String userId){
        List<Course> list = courseRepository.findAllByUserIdOrderByCreatTime(userId);
        return list;
    }
    //查找学习人数最多的课程
    public List<Course> findTopHotCourse(){
        List<Course> list = courseRepository.findTopByCountCourseRecord();
        return list;
    }
}
