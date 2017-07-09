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
    public void addCourseRecordsTest(List<User> usersList,List<Course> courses){
//        String users[] = {usersList.get(0).getObjectId(),usersList.get(1).getObjectId(),usersList.get(1).getObjectId(),
//                usersList.get(2).getObjectId(),usersList.get(2).getObjectId(),usersList.get(3).getObjectId(),
//                usersList.get(3).getObjectId(),usersList.get(4).getObjectId(),usersList.get(5).getObjectId(),
//                usersList.get(5).getObjectId(),usersList.get(6).getObjectId(),usersList.get(6).getObjectId()};
//        String course[] = {courses.get(1).getObjectId(),courses.get(0).getObjectId(),courses.get(2).getObjectId(),
//                courses.get(0).getObjectId(),courses.get(3).getObjectId(),courses.get(0).getObjectId(),
//                courses.get(4).getObjectId(),courses.get(9).getObjectId(),courses.get(5).getObjectId(),
//                            courses.get(6).getObjectId(),courses.get(8).getObjectId(),courses.get(7).getObjectId()};
        for(int i=0;i<usersList.size();i++){
            CourseRecord courseRecord = new CourseRecord(usersList.get(i).getObjectId(),courses.get(i).getObjectId());
            courseRecordRepository.save(courseRecord);
        }
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
            courseRecordRepository.save(courseRecord);
        }
    }
    //查询所有
    public List<CourseRecord> findAll(){
        List<CourseRecord> list = courseRecordRepository.findAll();
        return  list;
    }
    //主key查询
    public  CourseRecord findById(String id){
        return courseRecordRepository.findById(id);
    }
}
