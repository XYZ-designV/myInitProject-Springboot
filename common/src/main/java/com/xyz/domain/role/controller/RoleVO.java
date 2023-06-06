package com.xyz.domain.role.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "RoleVo - 返回角色数据", description = "返回角色数据")
public class RoleVO {

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @ApiModelProperty(value = "角色权限字符串")
    private String roleKey;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序")
    private Integer roleSort;

    /**
     * 角色状态（0正常 1停用）
     */
    @ApiModelProperty(value = "角色状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
