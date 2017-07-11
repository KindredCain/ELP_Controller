package com.elp.controller;

import com.elp.model.Course;
import com.elp.model.Office;
import com.elp.model.User;
import com.elp.repository.PagerankDao;
import com.elp.service.*;
import com.elp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by NWJ on 2017/7/2.
 */

@RestController
@CrossOrigin
public class TestController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/posttest")
    public Result postTest(String name) {
        return Result.success(name);
    }

    @GetMapping(value = "/gettest/{name}")
    public Result getTest(@PathVariable("name") String name) {
        return Result.success(name);
    }

    @PostMapping(value = "/maxuser")
    public Result maxUser() {
        return Result.success(userService.findMax());
    }
}
