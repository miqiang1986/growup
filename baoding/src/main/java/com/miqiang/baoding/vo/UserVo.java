package com.miqiang.baoding.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-07  13:33
 */
@Data
@Api("用户登陆成功后响应信息")
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("性别 1男2女")
    private String sex;

    private String token;

    private Object permissions;

}
