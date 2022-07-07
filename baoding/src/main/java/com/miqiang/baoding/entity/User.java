package com.miqiang.baoding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.miqiang.baoding.anotation.GwmParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author miqiang
 * @since 2022-07-07
 */
@Getter
@Setter
@TableName("tab_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("账号")
    @GwmParam()
    private String account;

    @ApiModelProperty("姓名")
    @GwmParam()
    private String name;

    @ApiModelProperty("年龄")
    @GwmParam()
    private Integer age;

    @ApiModelProperty("性别 1男2女")
    @GwmParam()
    private String sex;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐值")
    private String salt;

    @ApiModelProperty("状态 0锁定1正常")
    private Integer state;

    @ApiModelProperty("登录错误次数")
    private Integer logingErrCount;


}
