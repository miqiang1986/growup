package com.miqiang.baoding.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miqiang.baoding.common.PublicMsgs;
import com.miqiang.baoding.common.PublicParams;
import com.miqiang.baoding.entity.User;
import com.miqiang.baoding.service.IUserService;
import com.miqiang.baoding.util.AssertParam;
import com.miqiang.baoding.util.Result;
import com.miqiang.baoding.util.query.QueryGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miqiang
 * @since 2022-07-07
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/baoding/user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("findPage")
    @ApiOperation(value = "分页查询用户信息")
    public Result<Page<User>> findPage(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                       @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                       HttpServletRequest request, User user) {
        Page<User> page = new Page<User>(pageNo, pageSize);
        QueryWrapper<User> queryWrapper = QueryGenerator.initQueryWrapper(user, request.getParameterMap());
        return Result.OK(service.page(page, queryWrapper));
    }

    @PostMapping("save")
    @ApiOperation(value = "保存用户信息")
    public Result<?> save(@RequestBody User user) {
        String errMsg = AssertParam.verificationInterval(user);
        if (StringUtils.isNotBlank(errMsg)) {
            return Result.error(errMsg, null);
        }
        // 账号不能重复
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getAccount, user.getAccount())
                .ne(StringUtils.isNoneBlank(user.getId()), User::getId, user.getId());
        List<User> existDatas = service.list(queryWrapper);
        if (null != existDatas && !existDatas.isEmpty()) {
            return Result.error("账号不能重复！");
        }
        boolean isSuccess;
        if (StringUtils.isBlank(user.getId())) {
            // 默认值
            String realPass = DigestUtils.md5Hex(PublicParams.DEFAULT_PASS + user.getSalt());
            user.setPassword(realPass);
            user.setSalt(PublicParams.SALT);
            user.setState(PublicParams.STATE_ENABLE);
            user.setLogingErrCount(0);
            isSuccess = service.save(user);
        } else {
            User oldData = service.getById(user.getId());
            if (null == oldData) {
                return Result.error(PublicMsgs.UPDATE_DATA_NOT_EXIST);
            }
            if (!oldData.getAccount().equals(user.getAccount())) {
                return Result.error("不能修改账号！");
            }
            BeanUtils.copyProperties(user, oldData, "id", "account", "password", "salt", "state", "logingErrCount");
            isSuccess = service.updateById(user);
        }
        if (isSuccess) {
            return Result.OK(PublicMsgs.SAVE_SUCCESS);
        }
        return Result.error(PublicMsgs.SAVE_ERROR);
    }

    // 锁定/解锁
    // 初始化密码

}
