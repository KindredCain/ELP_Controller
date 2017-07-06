package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Department;
import com.elp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private BaseService baseService;

    //增
    public void add(Department department){
        departmentRepository.save(department);
    }
    //删
    public void delete(Department department){
        Department departmentItem = departmentRepository.findById(department.getObjectId());
        if(departmentItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            baseService.delete(departmentRepository, departmentItem);
        }
    }
    //改
    public void update(Department department){
        Department departmentItem = departmentRepository.findById(department.getObjectId());
        if(departmentItem == null){
            throw new MyException(ResultEnum.ERROR_101);
        } else{
            departmentRepository.save(department);
        }
    }
    //查询所有
    public List<Department> findAll(){
        List<Department> list = departmentRepository.findAll();
        return  list;
    }
    //主key查询
    public  Department findById(String id){
        return departmentRepository.findById(id);
    }
}
