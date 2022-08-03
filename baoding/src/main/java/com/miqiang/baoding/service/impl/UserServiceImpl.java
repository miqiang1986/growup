package com.miqiang.baoding.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.miqiang.baoding.entity.User;
import com.miqiang.baoding.mapper.UserMapper;
import com.miqiang.baoding.service.IUserService;
import com.miqiang.baoding.util.Result;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author miqiang
 * @since 2022-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getByAccount(String accout) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getAccount, accout);
        return this.getOne(queryWrapper);
    }

    @Override
    @SentinelResource(value = "testCentinel", fallback = "backTest")
    public Result<?> testCentinel() {
        return Result.OK("ok");
    }

    public Result<?> backTest() {
        return Result.error("To back...");
    }
}
