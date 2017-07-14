package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.LessonRecord;
import com.elp.repository.LessonRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class LessonRecordService {
    @Autowired
    private LessonRecordRepository lessonRecordRepository;
    @Autowired
    private BaseService baseService;

    //增
    public void add(LessonRecord lessonRecord){
        lessonRecordRepository.save(lessonRecord);
    }
    //删
    public void delete(LessonRecord lessonRecord){
        LessonRecord lessonRecordItem = lessonRecordRepository.findById(lessonRecord.getObjectId());
        if(lessonRecordItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(lessonRecordRepository, lessonRecordItem);
        }
    }
    //改
    public void update(LessonRecord lessonRecord){
        LessonRecord lessonRecordItem = lessonRecordRepository.findById(lessonRecord.getObjectId());
        if(lessonRecordItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            lessonRecordRepository.save(lessonRecord);
        }
    }
    //查询所有
    public List<LessonRecord> findAll(){
        List<LessonRecord> list = lessonRecordRepository.findAll();
        return  list;
    }
    //主key查询
    public LessonRecord findById(String id){
        return lessonRecordRepository.findById(id);
    }
    //课时id查询
    public List<LessonRecord> findAllByLessonNum(String lessonNum){
        return lessonRecordRepository.findAllByLessonNumAndDelTimeIsNull(lessonNum);
    }
}
