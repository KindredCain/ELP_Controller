package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Office;
import com.elp.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class OfficeService {
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private BaseService baseService;

    //增
    public void add(Office office){
        officeRepository.save(office);
    }
    //删
    public void delete(Office office){
        Office officeItem = officeRepository.findById(office.getObjectId());
        if(officeItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(officeRepository, officeItem);
        }
    }
    //改
    public void update(Office office){
        Office officeItem = officeRepository.findById(office.getObjectId());
        if(officeItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            officeRepository.save(office);
        }
    }
    //查询所有
    public List<Office> findAll(){
        List<Office> list = officeRepository.findAll();
        return  list;
    }
    public List<Object[]> findAllWithDepartment(){
        return officeRepository.findAllWithDepartment();
    }
    //主key查询
    public  Office findById(String id){
        return officeRepository.findById(id);
    }
}
