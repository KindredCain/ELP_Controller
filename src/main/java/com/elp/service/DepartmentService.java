package com.elp.service;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Department;
import com.elp.model.User;
import com.elp.repository.DepartmentRespositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/7/3.
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRespositroy departmentRespositroy;
    //增
    public void add(Department department){
        departmentRespositroy.save(department);
    }
    //查找所有
    public List<Department> allDepartment(){
        List<Department> list = departmentRespositroy.findAll();
        return  list;
    }
    //精确查找
    public  List<Department> findById(String id){
        return departmentRespositroy.findById(id);
    }

    //修改
    public void updateDepartment(Department department){
        Department departmentOne = departmentRespositroy.findOne(department.getObjectId());
        if(departmentOne == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            departmentRespositroy.save(department);
        }
    }
    //删除
    public void delDepartment(String id){
        Department department = departmentRespositroy.findOne(id);
        if(department == null){
            throw new MyException(ResultEnum.ERROR_101);
        }else{
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            department.setDelTime(time);
            departmentRespositroy.save(department);
        }
    }
}
