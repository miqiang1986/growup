package com.miqiang.lixian.controller;

import com.miqiang.lixian.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-22  13:14
 */
@RequestMapping("test")
@RestController
public class TestController {

    @PostMapping("test")
    public Result<?> test() {
        return Result.OK("This is test!");
    }

}
