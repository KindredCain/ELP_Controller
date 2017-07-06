package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.CourseRecord;
import com.elp.model.User;
import com.elp.repository.CourseRecordRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class CourseRecordService {
    @Autowired
    private CourseRecordRespositroy courseRecordRespositroy;
    //增
    public void add(CourseRecord courseRecord){
        courseRecordRespositroy.save(courseRecord);
    }
    //查找所有
    public List<CourseRecord> allCourseRecord(){
        List<CourseRecord> list = courseRecordRespositroy.findAll();
        return  list;
    }
    //精确查找
    public  List<CourseRecord> findById(String id){
        return courseRecordRespositroy.findById(id);
    }

    //修改
    public void updateCourseRecord(CourseRecord courseRecord){
        CourseRecord courseRecordOne = courseRecordRespositroy.findOne(courseRecord.getObjectId());
        if(courseRecordOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{

            courseRecordRespositroy.save(courseRecord);
        }
    }
    //删除
    public void delCourseRecord(String id){
        CourseRecord courseRecordOne = courseRecordRespositroy.findOne(id);
        if(courseRecordOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            courseRecordOne.setDelTime(time);
            courseRecordRespositroy.save(courseRecordOne);
        }
    }
}
