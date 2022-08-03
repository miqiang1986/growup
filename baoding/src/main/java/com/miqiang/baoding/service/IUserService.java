package com.miqiang.baoding.service;

import com.miqiang.baoding.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.miqiang.baoding.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author miqiang
 * @since 2022-07-07
 */
public interface IUserService extends IService<User> {

    /**
     * 根据账号查询用户
     * @author miqiang
     * @date 2022/7/7 11:08
     * @param accout 账号
     * @return com.miqiang.baoding.entity.User
     **/
    User getByAccount(String accout);

    Result<?> testCentinel();
}
