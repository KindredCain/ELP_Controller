package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Lesson;
import com.elp.repository.LessonRecordRepository;
import com.elp.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
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
    @Autowired
    private LessonRecordRepository lessonRecordRepository;

    //增
    public void add(Lesson lesson){
        lessonRepository.save(lesson);
    }
    //删
    @Transactional
    public void delete(Lesson lesson){
        Lesson lessonItem = lessonRepository.findById(lesson.getObjectId());
        if(lessonItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(lessonRepository, lessonItem);
            Timestamp time = new Timestamp(new Date().getTime());
            lessonRecordRepository.deleteByLessonNum(lessonItem.getObjectId(), time);
        }
    }
    //改
    @Transactional
    public void update(List<Lesson> lessonList){
        for(int i = 0; i < lessonList.size(); i++) {
            Lesson lesson = lessonList.get(i);
            Lesson lessonItem = lessonRepository.findById(lesson.getObjectId());
            if (lessonItem == null) {
                throw new MyException(ResultEnum.ERROR_101);
            } else {
                lessonRepository.save(lesson);
            }
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
    //用户学习课时历史查询
    public List<Object[]> findAllWithLessonRecord(String userNum){
        return lessonRepository.findAllWithLessonRecord(userNum);
    }
    //课时主键历史查询
    public Object[] findByIdWithLessonRecord(String objectId, String userNum){
        return lessonRepository.findByIdWithLessonRecord(objectId, userNum);
    }
    //课程课时列表查询
    public List<Object[]> findByCourseNumWithLessonRecord(String courseNum, String userNum){
        return lessonRepository.findByCourseNumWithLessonRecord(courseNum, userNum);
    }
}
