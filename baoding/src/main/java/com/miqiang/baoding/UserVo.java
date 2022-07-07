package com.miqiang.baoding;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-07  13:33
 */
@Data
public class UserVo {

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
