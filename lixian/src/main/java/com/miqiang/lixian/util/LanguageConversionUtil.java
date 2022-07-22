package com.miqiang.lixian.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author miqiang
 * @version 1.0.0
 * @description 中英文转码工具类
 * @createTime 2021年09月01日 17:21:00
 */
public class LanguageConversionUtil {

    public static final Locale localeEn = new Locale("en", "US");

    public static final Locale localeZh = new Locale("zh", "CN");

    /**
     * @description 根据语言类型获取key对应的值。注意：中文文件必须是UTF-8格式
     * @param: key  content_*.properties 相关文件中存在的key
     * @param: language 语言标识
     * @author miqiang
     * @createTime 2021/9/1 18:53
     * @updateTime 2021/9/1 18:53
     * @return: java.lang.String 如果文件中没有配置key，则返回空字符串
     */
    public static String getContent(String key, String language, String... vals) {
        Locale locale = "en-us".equals(language) ? localeEn : localeZh;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("content.content", locale);
        String val = null;
        try {
            val = resourceBundle.getString(key);
//            val = new String(resourceBundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
        } catch (MissingResourceException mre) {
            return "";
//        } catch (UnsupportedEncodingException e) {
//            throw new JeecgBootException("中英文转码失败！");
        }
        if (null != vals && vals.length > 0) {
            return String.format(val, vals);
        }
        return val;
    }

    /**
     * @description 根据语言类型获取key对应的值。注意：中文文件必须是UTF-8格式
     * @param: key  content_*.properties 相关文件中存在的key
     * @param: request HttpServletRequest
     * @author miqiang
     * @createTime 2021/9/2 9:26
     * @updateTime 2021/9/2 9:26
     * @return: java.lang.String 如果文件中没有配置key，则返回空字符串
     */
    public static String getContent(String key, HttpServletRequest request, String... vals) {
        return getContent(key, request.getHeader("X-Access-Language"), vals);
    }

}