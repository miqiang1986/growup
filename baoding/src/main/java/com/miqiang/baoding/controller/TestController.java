package com.miqiang.baoding.controller;

import com.miqiang.baoding.feign.TestFeign;
import com.miqiang.baoding.service.IUserService;
import com.miqiang.baoding.util.RedisUtil;
import com.miqiang.baoding.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author miqiang
 * @createTime 2022-07-07  09:38
 * @version 1.0
 */
@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private TestFeign testFeign;
    @Autowired
    private IUserService userService;

    /**
     * test redis set
     * @author miqiang
     * @date 2022/7/7 9:54
     * @param key   redisKey
     * @param val   save value
     * @return com.miqiang.baoding.util.Result<?>
     **/
    @RequestMapping("testRedis")
    public Result<?> testRedis(String key, String val) {
        boolean set = redisUtil.set(key, val);
        if (set) {
            return Result.OK();
        }
        return Result.error("设置失败!");
    }

    @RequestMapping("testRedisGet")
    public Result<?> testGetRedis(String key) {
        Object o = redisUtil.get(key);
        return Result.OK(o);
    }

    @PostMapping("test")
    public Result<?> test() {
        return testFeign.test();
    }

    // sentinel监控测试---注解
    @PostMapping("testCentinel")
    public Result<?> testCentinel() {
        return userService.testCentinel();
    }

}
