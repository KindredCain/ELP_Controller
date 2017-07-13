package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.ShowUser;
import com.elp.model.User;
import com.elp.repository.LessonRecordRepository;
import com.elp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BaseService baseService;
    @Autowired
    private LessonRecordRepository lessonRecordRepository;

    //增
    public void add(User user){
        userRepository.save(user);
    }
    //删
    @Transactional
    public void delete(User user){
        User userItem = userRepository.findById(user.getObjectId());
        if(userItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(userRepository, userItem);
            Timestamp time = new Timestamp(new Date().getTime());
            lessonRecordRepository.deleteByUserNum(user.getObjectId(), time);
        }
    }
    //改
    public void update(User user){
        User userItem = userRepository.findById(user.getObjectId());
        if(userItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            userRepository.save(user);
        }
    }
    //改权限
    public void updatePower(User user){
        User userItem = userRepository.findById(user.getObjectId());
        if(userItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            userItem.setUserPower(user.getUserPower());
            userRepository.save(userItem);
        }
    }
    //改密码
    public void updatePwd(User user){
        User userItem = userRepository.findById(user.getObjectId());
        if(userItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            userItem.setPwd("12345678");
            userRepository.save(userItem);
        }
    }
    //查询所有
    public List<User> findAll(){
        List<User> list = userRepository.findAll();
        return  list;
    }
    //主key查询
    public  User findById(String id){
        return userRepository.findById(id);
    }
    //职位查询
    public List<User> findByOffice(String officeNum){
        return  userRepository.findByOfficeNum(officeNum);
    }
    //类型查询
    public List<User> findByUserType(String userType){
        return userRepository.findByUserType(userType);
    }
    //登录
    public User findByLogIdAndPwd(String logId, String pwd){
        return userRepository.findByLogIdAndPwdAndDelTimeIsNull(logId, pwd);
    }
    //根据用户登录id查询
    public User findByLogId(String logId){
        return userRepository.findByLogId(logId);
    }
    //最多课时用户查询
    public List<ShowUser> findMax(){
        return userRepository.findMax();
    }
    //他人查询用户
    public ShowUser findByLogIdFromOther(String logId){
        return userRepository.findByLogIdFromOther(logId);
    }
}
