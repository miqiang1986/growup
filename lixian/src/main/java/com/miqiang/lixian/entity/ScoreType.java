package com.miqiang.lixian.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author miqiang
 * @since 2022-07-22
 */
@Getter
@Setter
@TableName("tab_score_type")
@ApiModel(value = "ScoreType对象", description = "")
public class ScoreType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("分数")
    private BigDecimal num;


}
