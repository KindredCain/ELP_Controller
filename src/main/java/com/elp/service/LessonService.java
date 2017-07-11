package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Lesson;
import com.elp.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private BaseService baseService;

    //增
    public void add(Lesson lesson){
        lessonRepository.save(lesson);
    }
    //删
    public void delete(Lesson lesson){
        Lesson lessonItem = lessonRepository.findById(lesson.getObjectId());
        if(lessonItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(lessonRepository, lessonItem);
        }
    }
    //改
    public void update(Lesson lesson){
        Lesson lessonItem = lessonRepository.findById(lesson.getObjectId());
        if(lessonItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            lessonRepository.save(lesson);
        }
    }
    //查询所有
    public List<Lesson> findAll(){
        List<Lesson> list = lessonRepository.findAll();
        return  list;
    }
    //根据用户编号和课程编号查找对应的课时和课时记录
    public List<Object[]> findAllByCourseNumAndUserNum(String courseNum,String userNum){
        List<Object[]> list = lessonRepository.findAllByUserNumAndCourseNum(courseNum,userNum);
        return list;
    }

    //主key查询
    public  Lesson findById(String id){
        return lessonRepository.findById(id);
    }
}
