package com.miqiang.baoding.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.miqiang.baoding.anotation.GwmParam;
import com.miqiang.baoding.common.PublicParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author miqiang
 * @since 2022-08-16
 */
@Getter
@Setter
@TableName("tab_permission")
@ApiModel(value = "Permission对象", description = "")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("父id")
    private String parentId;

    @ApiModelProperty("名称")
    @GwmParam
    private String name;

    @ApiModelProperty("启用状态(0禁用1启用)")
    private String status = PublicParams.YES_NO_Y;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("菜单路径")
    private String url;

    @ApiModelProperty("菜单排序")
    private Integer sortNo;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("最后修改时间")
    private Date updateTime;

    /**菜单所属类型(0学生1家长,非0或非1只有管理员能看)*/
    @ApiModelProperty("菜单所属类型")
    @GwmParam
    private String perType;

    @ApiModelProperty("是否已发布(0否1是)")
    private String isRelease = PublicParams.YES_NO_N;

    @ApiModelProperty("是否为游戏(0否1是)")
    private String isPlay = PublicParams.YES_NO_N;


}
