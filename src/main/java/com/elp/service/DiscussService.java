package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Discuss;
import com.elp.repository.DiscussRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class DiscussService {
    @Autowired
    private DiscussRepository discussRepository;
    @Autowired
    private BaseService baseService;

    //增
    public void add(Discuss discuss){
        discussRepository.save(discuss);
    }
    //删
    public void delete(Discuss discuss){
        Discuss discussItem = discussRepository.findById(discuss.getObjectId());
        if(discussItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(discussRepository, discussItem);
        }
    }
    //改
    public void update(Discuss discuss){
        Discuss discussItem = discussRepository.findById(discuss.getObjectId());
        if(discussItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            discussRepository.save(discuss);
        }
    }
    //查询所有
    public List<Discuss> findAll(){
        List<Discuss> list = discussRepository.findAll();
        return  list;
    }
    //根据讨论id找到讨论、用户、课时和课程
    public List<Object[]> findByIdWithLessonAndCourse(String discussId){
        return discussRepository.findByIdWithLessonAndCourse(discussId);
    }
    //查找讨论和对应的用户
    public List<Object[]> findAllByLessonId(String lessonId){
        return discussRepository.findByLessonNum(lessonId);
    }
    //主key查询
    public  Discuss findById(String id){
        return discussRepository.findById(id);
    }
}
