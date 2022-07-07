package com.miqiang.baoding.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-07  17:45
 */
public class AssertUtil {

    /**
     * 判断String是否有值， 如果字符串为null 或者为 ""（空字符串）则返回false1
     * @param val String
     * @return boolean
     */
    public static boolean isVal(String val) {
        if (val == null || val.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 判断Long 类型数值是否有值，如果val = null 或者val ==0 则返回false; 否则返回true
     * @param val
     * @return
     */
    public static boolean isVal(Long val) {
        if (val == null || val.longValue() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 判断
     * @param val
     * @return
     */
    public static boolean isVal(Object val) {
        if (val == null) {
            return false;
        }
        if (val instanceof String && val.toString().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 判断Map是否为空
     * @param val
     * @return boolean
     */
    public static boolean isVal(Map<?,?> val) {
        if (val == null || val.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 判断集合是否为空
     * @param val
     * @return boolean
     */
    public static boolean isVal(Collection<?> val) {
        if (val == null || val.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 判断byte[]是否为空
     * @param val
     * @return boolean
     */
    public static boolean isVal(byte[] val) {
        if (val == null || val.length <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 获取所有字段为null的属性名
     * 用于BeanUtils.copyProperties()拷贝属性时，忽略空值
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
