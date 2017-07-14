package com.elp.controller;

import com.elp.model.Msg;
import com.elp.service.MsgService;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class MsgController {
    @Autowired
    private MsgService msgService;

    @PostMapping(value = "/viewusermsg")
    public Result viewUserMsg(@RequestParam("userId") String userid){
        List<Msg> list = msgService.findByRecUser(userid);
        List<Map> mapList = new ArrayList();
        for(int i=0;i<list.size();i++){
            Map tempMap = new HashMap();
            Msg msg = list.get(i);
            tempMap.put("msg",msg);
            mapList.add(tempMap);
        }
        Map returnMap = new HashMap();
        returnMap.put("msgs",mapList);
        return Result.success(returnMap);
    }
    @PostMapping(value = "/updatemsgstate")
    public Result updateMsgState(Msg msg){
        msgService.updateMsgStats(msg);
        return Result.success();
    }
    @PostMapping(value = "/deletemsg")
    public Result deleteMsg(Msg msg){
        msgService.delete(msg);
        return Result.success();
    }
}

