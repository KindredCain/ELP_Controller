package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Course;
import com.elp.model.RelationCourse;
import com.elp.repository.RelationCourseRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/4.
 */
@Service
public class RelationCoursService {
    @Autowired
    private RelationCourseRespositroy relationCourseRespositroy;
    //增
    public void add(RelationCourse relationCours){
        relationCourseRespositroy.save(relationCours);
    }
    //查找所有
    public List<RelationCourse> allCourse(){
        List<RelationCourse> list = relationCourseRespositroy.findAll();
        return  list;
    }
    //精确查找
    public  List<RelationCourse> findById(String id){
        return relationCourseRespositroy.findById(id);
    }

    //修改
    public void updateRelationCourse(RelationCourse relationCourse){
        RelationCourse relationCourseOne = relationCourseRespositroy.findOne(relationCourse.getObjectId());
        if(relationCourseOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            relationCourse.setUpdateTime(time);
            relationCourseRespositroy.save(relationCourse);
        }
    }
    //删除
    public void delRelationCourse(String id){
        RelationCourse relationCourseOne = relationCourseRespositroy.findOne(id);
        if(relationCourseOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            relationCourseOne.setDelTime(time);
            relationCourseRespositroy.save(relationCourseOne);
        }
    }
}
