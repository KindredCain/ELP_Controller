package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Course;
import com.elp.model.CourseRecord;
import com.elp.model.User;
import com.elp.repository.CourseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class CourseRecordService {
    @Autowired
    private CourseRecordRepository courseRecordRepository;
    @Autowired
    private BaseService baseService;

    //增
    public void add(CourseRecord courseRecord){
        courseRecordRepository.save(courseRecord);
    }
    //删
    public void delete(CourseRecord courseRecord){
        CourseRecord courseRecordItem = courseRecordRepository.findById(courseRecord.getObjectId());
        if(courseRecordItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(courseRecordRepository, courseRecordItem);
        }
    }
    //改
    public void update(CourseRecord courseRecord){
        CourseRecord courseRecordItem = courseRecordRepository.findById(courseRecord.getObjectId());
        if(courseRecordItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            courseRecordItem.setCourseComplete(courseRecord.getCourseComplete());
            courseRecordRepository.save(courseRecordItem);
        }
    }
    //查询所有
    public List<CourseRecord> findAll(){
        List<CourseRecord> list = courseRecordRepository.findAll();
        return  list;
    }
    //根据课程id查找课程统计情况
    public List<CourseRecord> findAllByCourseNum(String courseNum){
        List<CourseRecord> list = courseRecordRepository.findByCourseNum(courseNum);
        return list;
    }
    //根据用户id查找课程统计情况
    public List<CourseRecord> findAllByUserNum(String userNum){
        List<CourseRecord> list = courseRecordRepository.findByUserNum(userNum);
        return list;
    }
    //主key查询
    public  CourseRecord findById(String id){
        return courseRecordRepository.findById(id);
    }

}
