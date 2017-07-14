package com.elp.controller;

import com.elp.model.Department;
import com.elp.model.Office;
import com.elp.service.OfficeService;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ASUS on 2017/7/13.
 */
@RestController
@CrossOrigin
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @PostMapping(value = "/viewalloffice")
    public Result viewAllOffice(){
        List<Object[]> list = officeService.findAllWithDepartment();
        Map returmMap = new HashMap<>();
        List tempMapList = new ArrayList();
        for (int i=0;i<list.size();i++) {
            Object[] objects = list.get(i);
            Office office = (Office) objects[0];
            Department department = (Department) objects[1];
            Map tempMap = new HashMap();
            tempMap.put("office",office);
            tempMap.put("department",department);
            tempMapList.add(tempMap);
        }
        returmMap.put("officelist",tempMapList);
        return Result.success(returmMap);
    }
}
