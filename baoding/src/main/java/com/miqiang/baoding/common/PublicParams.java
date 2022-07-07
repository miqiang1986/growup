package com.miqiang.baoding.common;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-07  10:19
 */
public class PublicParams {

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASS = "123456";
    /**
     * 密钥
     */
    public static final String SALT = "salt";

    /**
     * 状态-禁用
     */
    public static final Integer STATE_UNABLE = 0;
    /**
     * 状态-启用
     */
    public static final Integer STATE_ENABLE = 1;

    /**
     * 最大登录错误次数
     */
    public static final Integer MAX_LOGIN_ERR = 3;

}
