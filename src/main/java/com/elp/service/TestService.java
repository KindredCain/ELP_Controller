package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.User;
import com.elp.repository.UserRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class TestService {
    @Autowired
    private UserRespositroy userRespositroy;
    //增
    public void add(User user){
        userRespositroy.save(user);
    }
    //查找所有
    public List<User> allUser(){
        List<User> list = userRespositroy.findAll();
        return  list;
    }
    //精确查找 根据编号  职位编号 根据用户类型
    public  List<User> findById(String id){
        return userRespositroy.findById(id);
    }
    public List<User> findByOffice(String officeNum){
        return  userRespositroy.findByOfficeNum(officeNum);
    }
    public List<User> findByUserType(String userType){
        return userRespositroy.findByUserType(userType);
    }
    //修改用户信息
    public void updateUser(User user){
        User userone = userRespositroy.findOne(user.getObjectId());
        if(userone == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            user.setDelTime(time);
            userRespositroy.save(user);
        }
    }
    //删除用户
    public void delUser(String id){
        User userone = userRespositroy.findOne(id);
        if(userone == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            userone.setDelTime(time);
            userRespositroy.save(userone);
        }
    }
}
