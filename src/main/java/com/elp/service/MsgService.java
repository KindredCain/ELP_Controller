package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Msg;
import com.elp.repository.MsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class MsgService {
    @Autowired
    private MsgRepository msgRepository;
    @Autowired
    private BaseService baseService;

    //增
    public void add(Msg msg){
        msgRepository.save(msg);
    }
    //删
    public void delete(Msg msg){
        Msg msgItem = msgRepository.findById(msg.getObjectId());
        if(msgItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(msgRepository, msgItem);
        }
    }
    //改
    public void update(Msg msg){
        Msg msgItem = msgRepository.findById(msg.getObjectId());
        if(msgItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            msgRepository.save(msg);
        }
    }
    //查询所有
    public List<Msg> findAll(){
        List<Msg> list = msgRepository.findAll();
        return  list;
    }
    //主key查询
    public  Msg findById(String id){
        return msgRepository.findById(id);
    }
    //用户id查询
    public List<Msg> findByRecUser(String userId){
        return msgRepository.findByRecUser(userId);
    }
    //修改消息状态
    public void updateMsgStats(Msg msg){
        Msg msgItem = msgRepository.findById(msg.getObjectId());
        if(msgItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            msgItem.setMsgStats("readed");
            msgRepository.save(msgItem);
        }
    }
}
