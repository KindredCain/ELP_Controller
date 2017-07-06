package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.LessonRecord;
import com.elp.model.User;
import com.elp.repository.LessonRecordRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class LessonRecordService {
    @Autowired
    private LessonRecordRespositroy lessonRecordRespositroy;
    //增
    public void add(LessonRecord lessonRecord){
        lessonRecordRespositroy.save(lessonRecord);
    }
    //查找所有
    public List<LessonRecord> allLessRecord(){
        List<LessonRecord> list = lessonRecordRespositroy.findAll();
        return  list;
    }
    //精确查找
    public  List<LessonRecord> findById(String id){
        return lessonRecordRespositroy.findById(id);
    }

    //修改
    public void updateLessonRecord(LessonRecord lessonRecord){
        LessonRecord lessonRecordOne = lessonRecordRespositroy.findOne(lessonRecord.getObjectId());
        if(lessonRecordOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            lessonRecordRespositroy.save(lessonRecord);
        }
    }
    //删除
    public void delLessonRecord(String id){
        LessonRecord lessonRecordOne = lessonRecordRespositroy.findOne(id);
        if(lessonRecordOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            lessonRecordOne.setDelTime(time);
            lessonRecordRespositroy.save(lessonRecordOne);
        }
    }
}
