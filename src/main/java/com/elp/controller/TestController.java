package com.elp.controller;

import com.elp.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * Created by NWJ on 2017/7/2.
 */

@RestController
@CrossOrigin
public class TestController {

    @PostMapping(value = "/posttest")
    public Result postTest(String name) {
        return Result.success(name);
    }

    @GetMapping(value = "/gettest/{name}")
    public Result getTest(@PathVariable("name") String name) {
        return Result.success(name);
    }

}
