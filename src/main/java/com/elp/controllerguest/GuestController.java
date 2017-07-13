package com.elp.controllerguest;

import com.elp.enums.ResultEnum;
import com.elp.exception.MyException;
import com.elp.model.Office;
import com.elp.model.User;
import com.elp.service.OfficeService;
import com.elp.service.UserService;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NWJ on 2017/7/12.
 */

@RestController
@CrossOrigin
public class GuestController {
    @Autowired
    private UserService userService;
    @Autowired
    private OfficeService officeService;

    @PostMapping(value = "/login")
    public Result login (User user, HttpSession session){
        User loginUser = userService.findByLogIdAndPwd(user.getLogId(), user.getPwd());
        if(loginUser != null){
            session.setAttribute("ID", loginUser.getObjectId());
            session.setAttribute("TYPE", loginUser.getUserType());
            loginUser.setPwd(null);
            Office office = officeService.findById(loginUser.getOfficeNum());
            Map result = new HashMap();
            result.put("User", loginUser);
            result.put("Office", office);
            result.put("SessionId", session.getId());
            return Result.success(result);
        } else {
            throw new MyException(ResultEnum.ERROR_102);
        }
    }
}
