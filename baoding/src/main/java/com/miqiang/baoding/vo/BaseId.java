package com.miqiang.baoding.vo;

import com.miqiang.baoding.anotation.GwmParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-08  15:00
 */
@Data
@Api("单条数据修改或删除基础信息")
public class BaseId implements Serializable {

    @ApiModelProperty("主键id")
    @GwmParam()
    private String id;

}
