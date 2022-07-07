package com.miqiang.baoding.util;

public class CommonConstant {

    /**字典翻译文本后缀*/
    public static final String DICT_TEXT_SUFFIX = "_dictText";

    /**
     * 文件上传类型（本地：local，Minio：minio，阿里云：alioss）
     */
    public static final String UPLOAD_TYPE_LOCAL = "local";
    public static final String UPLOAD_TYPE_MINIO = "minio";
    public static final String UPLOAD_TYPE_OSS = "alioss";

    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;
    /** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /**访问权限认证未通过 510*/
    public static final Integer SC_JEECG_NO_AUTHZ=510;

    /** 登录用户Shiro权限缓存KEY前缀 */
    public static final String PREFIX_USER_SHIRO_CACHE  = "shiro:cache:org.jeecg.config.shiro.ShiroRealm.authorizationCache:";
    /** 登录用户Token令牌缓存KEY前缀 */
    public static final String PREFIX_USER_TOKEN  = "prefix_user_token_";

    /**
     * 微服务读取配置文件属性 服务地址
     */
    public static final String CLOUD_SERVER_KEY = "spring.cloud.nacos.discovery.server-addr";

    public static final String LANGUAGE_EN = "en-us";
    public static final String LANGUAGE_CN = "zh-cn";
    public static final String X_ACCESS_TOKEN = "X-Access-Token";
    public static final String X_ACCESS_LANGUAGE = "X-Access-Language";

    /**
     * 多租户 请求头
     */
    public static final String TENANT_ID = "tenant-id";
}
