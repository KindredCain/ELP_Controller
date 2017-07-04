package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Msg;
import com.elp.model.Office;
import com.elp.model.User;
import com.elp.repository.MsgRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class MsgService {
    @Autowired
    private MsgRespositroy MsgRespositroy;
    //增
    public void add(Msg msg){
        MsgRespositroy.save(msg);
    }
    //查找所有
    public List<Msg> allUser(){
        List<Msg> list = MsgRespositroy.findAll();
        return  list;
    }
    //精确查找
    public  List<Msg> findById(String id) {
        return MsgRespositroy.findById(id);
    }
    //修改信息
    public void updateMsg(Msg msg){
        Msg msgone = MsgRespositroy.findOne(msg.getObjectId());
        if(msgone == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            msg.setUpdateTime(time);
            MsgRespositroy.save(msg);
        }
    }
    //删除
    public void delMsg(String id){
        Msg msgone = MsgRespositroy.findOne(id);
        if(msgone == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            msgone.setDelTime(time);
            MsgRespositroy.save(msgone);
        }
    }
}
