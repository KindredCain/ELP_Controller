package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Office;
import com.elp.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class OfficeService {
    @Autowired
    private OfficeRepository officeRespositroy;
    //增
    public void add(Office office){
        officeRespositroy.save(office);
    }
    //查找所有
    public List<Office> allOffice(){
        List<Office> list = officeRespositroy.findAll();
        return  list;
    }
    //精确查找
    public  List<Office> findById(String id){
        return officeRespositroy.findById(id);
    }
    //修改
    public void updateUser(Office office){
        Office office1one = officeRespositroy.findOne(office.getObjectId());
        if(office1one == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            officeRespositroy.save(office);
        }
    }
    //删除
    public void delUser(String id){
        Office officeone = officeRespositroy.findOne(id);
        if(officeone == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            officeone.setDelTime(time);
            officeRespositroy.save(officeone);
        }
    }
}
