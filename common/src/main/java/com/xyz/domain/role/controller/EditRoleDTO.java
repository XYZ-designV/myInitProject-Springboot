package com.xyz.domain.role.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value = "RoleDTO - 修改角色数据", description = "修改角色数据")
public class EditRoleDTO {
    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id",required = true)
    private Long id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

    /**
     * 角色权限字符串
     */
    @NotBlank(message = "角色权限字符串不能为空")
    @ApiModelProperty(value = "角色权限字符串", required = true)
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

}
