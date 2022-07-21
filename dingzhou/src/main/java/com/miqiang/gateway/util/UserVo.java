package com.miqiang.gateway.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-07  13:33
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    private String id;

    // 账号
    private String account;

    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 性别 1男2女
    private String sex;

    private String token;

    private Object permissions;

}
