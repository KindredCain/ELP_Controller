package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Discuss;
import com.elp.model.User;
import com.elp.repository.DiscussRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class DiscussService {
    @Autowired
    private DiscussRespositroy discussRespositroy;

    //增
    public void add(Discuss discuss) {
        discussRespositroy.save(discuss);
    }

    //查找所有
    public List<Discuss> allDiscuss() {
        List<Discuss> list = discussRespositroy.findAll();
        return list;
    }

    //精确查找
    public List<Discuss> findById(String id) {
        return discussRespositroy.findById(id);
    }

    public List<Map<String,String>> findDiscussTest(){
        List list = discussRespositroy.findTest();
        List<Map<String,String>> listmap = new ArrayList();
        for (int i=0;i<list.size();i++){
            Map<String,String> map = new HashMap<String,String>();
            Object[] objects = (Object[]) list.get(i);
            Discuss discuss = (Discuss) objects[0];
            User user = (User) objects[1];
            map.put("discussContent",discuss.getDiscussContent());
            map.put("userName",user.getUserName());
            listmap.add(map);
        }
        return listmap;
    }

    //修改
    public void updateDiscuss(Discuss discuss) {
        Discuss discussOne = discussRespositroy.findOne(discuss.getObjectId());
        if (discussOne == null) {
            throw new MyException(ResultEnum.ERROR_101);
        } else {
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            discuss.setUpdateTime(time);
            discussRespositroy.save(discuss);
        }
    }

    //删除
    public void delDiscuss(String id) {
        Discuss dicussOne = discussRespositroy.findOne(id);
        if (dicussOne == null) {
            throw new MyException(ResultEnum.ERROR_101);
        } else {
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            dicussOne.setDelTime(time);
            discussRespositroy.save(dicussOne);
        }
    }
}
