package com.elp.controller;

import com.elp.model.Msg;
import com.elp.service.MsgService;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by ASUS on 2017/7/13.
 */
@RestController
@CrossOrigin
public class MsgController {
    @Autowired
    private MsgService msgService;

    @PostMapping(value = "viewusermsg")
    public Result viewUserMsg(){
//        List<Msg> list = msgService.fi
        return Result.success();
    }
}
