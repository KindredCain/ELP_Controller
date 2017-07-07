package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.User;
import com.elp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //增
    public void add(User user){
        userRepository.save(user);
    }
    //删
    public void delete(User user){
        User userItem = userRepository.findById(user.getObjectId());
        if(userItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(userRepository, userItem);
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
}
