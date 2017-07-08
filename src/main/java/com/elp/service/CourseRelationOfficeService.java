package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Course;
import com.elp.model.CourseRelationOffice;
import com.elp.model.Office;
import com.elp.model.User;
import com.elp.repository.CourseRelationOfficeRepository;
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

    //增
    public void add(CourseRelationOffice courseRelationOffice){
        courseRelationOfficeRepository.save(courseRelationOffice);
    }
    public void addCourseRelationOfficesTest(List<Office> offices, List<Course> courses){
//        String office[] = {"402805815d1bacfd015d1bad33ae0000","402805815d1bacfd015d1bad34520001"};
//        String courseNames[] = {"Java","JavaEE","Spring","Mybatis","SpringCloud",
//                "React","Bootstrap","HTML","css"};
        for(int i=0;i<courses.size();i++){
            if (i < 5) {
                CourseRelationOffice courseRelationOffice = new CourseRelationOffice(offices.get(0).getObjectId(),courses.get(i).getObjectId());
                courseRelationOfficeRepository.save(courseRelationOffice);
            } else {
                CourseRelationOffice courseRelationOffice = new CourseRelationOffice(offices.get(1).getObjectId(),courses.get(i).getObjectId());
                courseRelationOfficeRepository.save(courseRelationOffice);
            }
        }
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
    //主key查询
    public  CourseRelationOffice findById(String id){
        return courseRelationOfficeRepository.findById(id);
    }
}
