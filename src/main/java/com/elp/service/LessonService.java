package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Lesson;
import com.elp.repository.CourseRepository;
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
    @Autowired
    private CourseRepository courseRepository;

    //增
    @Transactional
    public void add(Lesson lesson) {
        lessonRepository.save(lesson);
        courseRepository.addCourseSumLesson(lesson.getCourseNum());
    }

    //删
    @Transactional
    public void delete(Lesson lesson) {
        Lesson lessonItem = lessonRepository.findById(lesson.getObjectId());
        if (lessonItem == null) {
            throw new MyException(ResultEnum.ERROR_101);
        } else {
            baseService.delete(lessonRepository, lessonItem);
            courseRepository.subCourseSumLesson(lesson.getCourseNum());
            Timestamp time = new Timestamp(new Date().getTime());
            lessonRecordRepository.deleteByLessonNum(lessonItem.getObjectId(), time);
        }
    }

    //改
    public void update(Lesson lesson) {
        Lesson lessonItem = lessonRepository.findById(lesson.getObjectId());
        if (lessonItem == null) {
            throw new MyException(ResultEnum.ERROR_101);
        } else {
            lessonRepository.save(lesson);
        }
    }

    //修改课时序列
    public void updateLessonOrder(List<Lesson> lessonList){
        for(int i = 0; i < lessonList.size(); i++){
            Lesson lesson = lessonList.get(i);
            Lesson lessonItem = lessonRepository.findById(lesson.getObjectId());
            if (lessonItem == null) {
                throw new MyException(ResultEnum.ERROR_101);
            } else {
                lessonItem.setLessonOrder(lesson.getLessonOrder());
                String lessonName = lessonItem.getLessonName();
                if(lessonName.indexOf("-") >= 0) {
                    lessonName = lesson.getLessonName() + "-" + lessonName.substring(lessonName.indexOf("-") + 1);
                } else {
                    lessonName = lesson.getLessonName() + "-" + lessonName;
                }
                lessonItem.setLessonName(lessonName);
                lessonRepository.save(lessonItem);
            }
        }
    }

    //查询所有
    public List<Lesson> findAll() {
        List<Lesson> list = lessonRepository.findAll();
        return list;
    }

    //主key查询
    public Lesson findById(String id) {
        return lessonRepository.findById(id);
    }

    //用户学习课时历史查询
    public List<Object[]> findAllWithLessonRecord(String userNum) {
        return lessonRepository.findAllWithLessonRecord(userNum);
    }

    //课时主键历史查询
    public Object[] findByIdWithLessonRecord(String objectId, String userNum) {
        return lessonRepository.findByIdWithLessonRecord(objectId, userNum);
    }

    //课程课时列表查询
    public List<Object[]> findByCourseNumWithLessonRecord(String courseNum, String userNum) {
        return lessonRepository.findByCourseNumWithLessonRecord(courseNum, userNum);
    }
}
