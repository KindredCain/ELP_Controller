package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.CourseRelationOffice;
import com.elp.model.User;
import com.elp.repository.CourseRelationOfficeRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class CourseRelationOfficeService {
    @Autowired
    private CourseRelationOfficeRespositroy courseRelationOfficeRespositroy;
    //增
    public void add(CourseRelationOffice courseRelationOffice){
        courseRelationOfficeRespositroy.save(courseRelationOffice);
    }
    //查找所有
    public List<CourseRelationOffice> allCourseRelationOffice(){
        List<CourseRelationOffice> list = courseRelationOfficeRespositroy.findAll();
        return  list;
    }
    //精确查找
    public  List<CourseRelationOffice> findById(String id){
        return courseRelationOfficeRespositroy.findById(id);
    }

    //修改
    public void updateCourseRelationOffice(CourseRelationOffice courseRelationOffice){
        CourseRelationOffice courseRelationOfficeRespositroyOne = courseRelationOfficeRespositroy.findOne(courseRelationOffice.getObjectId());
        if(courseRelationOfficeRespositroyOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            courseRelationOffice.setUpdateTime(time);
            courseRelationOfficeRespositroy.save(courseRelationOffice);
        }
    }
    //删除
    public void delCourseRelationOffice(String id){
        CourseRelationOffice courseRelationOfficeOne = courseRelationOfficeRespositroy.findOne(id);
        if(courseRelationOfficeOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            courseRelationOfficeOne.setDelTime(time);
            courseRelationOfficeRespositroy.save(courseRelationOfficeOne);
        }
    }
}
