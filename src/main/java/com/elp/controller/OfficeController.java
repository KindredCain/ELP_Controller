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
        return Result.success(officeService.findAll());
    }
}
