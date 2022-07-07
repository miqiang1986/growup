package com.miqiang.baoding.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.miqiang.baoding.anotation.GwmParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-07  16:42
 */
@Slf4j
public class AssertParam {

    /**
     * 参数是否能为空
     */
    public static String verificationInterval(Object objDTO) {
        return verificationInterval(objDTO, objDTO.getClass());
    }

    /**
     * 参数是否能为空
     */
    public static String verificationInterval(Object objDTO, Class clazz) {
        if (AssertUtil.isVal(objDTO)) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(objDTO));
            Field[] var3 = clazz.getDeclaredFields();
            int var4 = var3.length;

            List<String> errList = new ArrayList<>();
            for (int var5 = 0; var5 < var4; ++var5) {
                Field field = var3[var5];
                GwmParam annotation = (GwmParam) field.getAnnotation(GwmParam.class);
                if (AssertUtil.isVal(annotation)) {
                    if (annotation.nestAssert()) {
                        String errStr = assertList(jsonObject, annotation, field);
                        if (StrUtil.isNotBlank(errStr)) {
                            errList.add(errStr);
                        }
                    } else {
                        String errStr = assertObj(jsonObject, annotation, field);
                        if (StrUtil.isNotBlank(errStr)) {
                            errList.add(errStr);
                        }
                    }
                }
            }

            errList.removeIf(o -> StrUtil.isBlank(o));
            return String.join(",", errList);
        }
        return "";
    }

    public static String assertList(JSONObject jsonObject, GwmParam annotation, Field field) {
        Class<?> type = field.getType();
        String name = field.getName();
        String errStr = assertObj(jsonObject, annotation, field);
        if (StrUtil.isNotBlank(errStr)) {
            return errStr;
        }
        ApiModelProperty apiModelProperty = (ApiModelProperty) field.getAnnotation(ApiModelProperty.class);
        //判断属性上是否有 ApiModelProperty
        String propertyName = "";
        if (AssertUtil.isVal(apiModelProperty)) {
            propertyName = apiModelProperty.value();
        }

        String value = annotation.value();

        if (StrUtil.isBlank(value)) {
            if (AssertUtil.isVal(propertyName)) {
                errStr = propertyName + "不能为空！";
            } else {
                errStr = name + " 不能为空！";
            }
        } else {
            errStr = value;
        }

        //嵌套list
        if (type.getName().contains("List") || type.getName().contains("JSONArray")) {
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(jsonObject.get(name)));
            if (array.size() == 0) {
                return errStr;
            } else {
                List<String> errList = new ArrayList<>();
                array.forEach(o -> errList.add(verificationInterval(o, annotation.nestAssertClazz())));
                errList.removeIf(o -> StrUtil.isBlank(o));
                if(errList.size() == 0){
                    return "";
                }
                if(StrUtil.isNotBlank(propertyName)){
                    return propertyName +  "：[" + String.join(",", errList) + "]";
                }else{
                    return name +  "：[" + String.join(",", errList) + "]";
                }
            }
        } else {

            //嵌套对象
            JSONObject jsonObjectSon = null;
            try {
                jsonObjectSon = JSONObject.parseObject(JSON.toJSONString(jsonObject.get(name)));
            } catch (Exception e) {
                log.info("不是正确的对象 : [{}]", jsonObject.get(name));
            }
            if (jsonObjectSon.size() == 0) {
                return errStr;
            }
            return verificationInterval(jsonObjectSon, annotation.nestAssertClazz());
        }
    }

    public static String assertObj(JSONObject jsonObject, GwmParam annotation, Field field) {
        String name = field.getName();
        String value = annotation.value();
        String errStr = "";
        if (StrUtil.isBlank(value)) {
            //判断属性上是否有 ApiModelProperty
            ApiModelProperty apiModelProperty = (ApiModelProperty) field.getAnnotation(ApiModelProperty.class);
            if (AssertUtil.isVal(apiModelProperty)) {
                errStr = apiModelProperty.value() + "不能为空！";
            } else {
                errStr = name + " 不能为空！";
            }
        } else {
            errStr = value;
        }
        Object o = jsonObject.get(name);
        if (!AssertUtil.isVal(o)) {
            return errStr;
        }
        if (field.getType().getName().contains("List") || field.getType().getName().contains("JSONArray")) {

            JSONArray array = JSONArray.parseArray(JSON.toJSONString(jsonObject.get(name)));
            if (array.size() == 0) {
                return errStr;
            }
        }
        return "";
    }
}
