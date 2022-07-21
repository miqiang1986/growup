package com.miqiang.baoding.controller;

import com.alibaba.fastjson.JSONObject;
import com.miqiang.baoding.vo.UserVo;
import com.miqiang.baoding.common.PublicMsgs;
import com.miqiang.baoding.common.PublicParams;
import com.miqiang.baoding.entity.User;
import com.miqiang.baoding.service.IUserService;
import com.miqiang.baoding.util.CommonConstant;
import com.miqiang.baoding.util.JwtUtil;
import com.miqiang.baoding.util.RedisUtil;
import com.miqiang.baoding.util.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-07  10:33
 */
@RestController
@RequestMapping("")
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("login")
    public Result<?> login(String account, String password) {
        if (StringUtils.isAllBlank(account, password)) {
            return Result.error(PublicMsgs.PARAM_EMPTY);
        }
        // 账号是否锁定
        User user = userService.getByAccount(account);
        if (!PublicParams.STATE_ENABLE.equals(user.getState())) {
            return Result.error(PublicMsgs.ACCOUNT_UNABLE);
        }
        // 前端传输解密
        // 密码验证
        String realPass = DigestUtils.md5Hex(password + user.getSalt());
        if (!realPass.equals(user.getPassword())) {
            int logingErrCount = Optional.of(user.getLogingErrCount()).orElse(0);
            user.setLogingErrCount(++logingErrCount);
            if (logingErrCount > PublicParams.MAX_LOGIN_ERR) {
                user.setState(PublicParams.STATE_UNABLE);
            }
            userService.updateById(user);
            return Result.error("账号或密码错误！");
        }
        user.setLogingErrCount(0);
        userService.updateById(user);
        // 返回用户信息
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        String token = JwtUtil.sign(account, user.getPassword());
        userVo.setToken(token);
        // 用户权限(前端用于控制按钮是否显示)
        // 所有字典翻译
        // 设置token缓存有效时间
        String val = JSONObject.toJSONString(userVo);
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + account, val, JwtUtil.EXPIRE_TIME*2 / 1000);
        return Result.OK("登录成功!", userVo);
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public Result<Object> logout(HttpServletRequest request) {
        //用户退出逻辑
        String token = request.getHeader(CommonConstant.X_ACCESS_TOKEN);
        if(StringUtils.isBlank(token)) {
            return Result.error("退出登录失败！");
        }
        String account = JwtUtil.getUsername(token);
        User user = userService.getByAccount(account);
        if(user!=null) {
            //清空用户登录信息
            redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + account);
            //调用shiro的logout
//            SecurityUtils.getSubject().logout();
            return Result.ok("退出登录成功！");
        }else {
            return Result.error("Token无效!");
        }
    }

}
