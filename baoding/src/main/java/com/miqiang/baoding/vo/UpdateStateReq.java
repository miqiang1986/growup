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
 * @createTime 2022-07-08  14:59
 */
@Data
@Api("修改状态请求信息")
public class UpdateStateReq extends BaseId implements Serializable {

    @ApiModelProperty("状态 0锁定1正常")
    @GwmParam()
    private Integer state;

}
