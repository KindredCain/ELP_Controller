package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Course;
import com.elp.model.CourseRelationOffice;
import com.elp.model.Office;
import com.elp.model.User;
import com.elp.repository.CourseRelationOfficeRepository;
import com.elp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class CourseRelationOfficeService {
    @Autowired
    private CourseRelationOfficeRepository courseRelationOfficeRepository;
    @Autowired
    private BaseService baseService;
    @Autowired
    private CourseRepository courseRepository;

    //增
    public void add(CourseRelationOffice courseRelationOffice){
        courseRelationOfficeRepository.save(courseRelationOffice);
    }
    //删
    public void delete(CourseRelationOffice courseRelationOffice){
        CourseRelationOffice courseRelationOfficeItem = courseRelationOfficeRepository.findById(courseRelationOffice.getObjectId());
        if(courseRelationOfficeItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(courseRelationOfficeRepository, courseRelationOfficeItem);
        }
    }
    //改
    public void update(CourseRelationOffice courseRelationOffice){
        CourseRelationOffice courseRelationOfficeItem = courseRelationOfficeRepository.findById(courseRelationOffice.getObjectId());
        if(courseRelationOfficeItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            courseRelationOfficeRepository.save(courseRelationOffice);
        }
    }
    //查询所有
    public List<CourseRelationOffice> findAll(){
        List<CourseRelationOffice> list = courseRelationOfficeRepository.findAll();
        return  list;
    }

    //查找所包并根据方向名分类
    public List<CourseRelationOffice> findAllGroupBySubjectName(){
        return courseRelationOfficeRepository.findAllGroupBySubjectName();
    }
    //根据用户id查询用户职位对应的所有方向
    public List<Object[]> findByUserIdGroupBySubjectName(String userId){
        List<Object[]> list = courseRelationOfficeRepository.findByUserIdGroupBySubjectName(userId);
        return list;
    }
    //主key查询
    public  CourseRelationOffice findById(String id){
        return courseRelationOfficeRepository.findById(id);
    }
}
