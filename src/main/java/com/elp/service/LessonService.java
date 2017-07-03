package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Lesson;
import com.elp.model.User;
import com.elp.repository.LessonRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class LessonService {
    @Autowired
    private LessonRespositroy lessonRespositroy;
    //增
    public void add(Lesson lesson){
        lessonRespositroy.save(lesson);
    }
    //查找所有
    public List<Lesson> allUser(){
        List<Lesson> list = lessonRespositroy.findAll();
        return  list;
    }
    //精确查找
    public  List<Lesson> findById(String id){
        return lessonRespositroy.findById(id);
    }

    //修改
    public void updateLesson(Lesson lesson){
        Lesson lessonone = lessonRespositroy.findOne(lesson.getObjectId());
        if(lessonone == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            lesson.setUpdateTime(time);
            lessonRespositroy.save(lesson);
        }
    }
    //删除
    public void delLesson(String id){
        Lesson lessonone = lessonRespositroy.findOne(id);
        if(lessonone == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            lessonone.setDelTime(time);
            lessonRespositroy.save(lessonone);
        }
    }
}
