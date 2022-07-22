package com.miqiang.lixian.anotation;

import java.lang.annotation.*;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-07  17:44
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GwmParam {
    String modified() default "";
    String value() default "";

    /**
     * 嵌套字段校验
     * @return
     */
    boolean nestAssert() default false;

    /**
     * 嵌套实体
     * @return
     */
    Class nestAssertClazz() default Object.class;
}
