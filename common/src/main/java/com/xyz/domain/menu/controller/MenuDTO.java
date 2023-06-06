package com.xyz.domain.menu.controller;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "MenuDTO - 菜单数据", description = "菜单数据")
@Data
public class MenuDTO {
    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单主键Id")
    private Long id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", required = true)
    private String menuName;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID", required = true)
    private Long parentId;

    /**
     * 显示顺序
     */
    @ApiModelProperty("排序")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址", required = true)
    private String path;

    /**
     * 组件路径
     */
    @ApiModelProperty(value = "主键路径", required = true)
    private String component;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @ApiModelProperty(value = "菜单类型", required = true)
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @ApiModelProperty("菜单状态")
    private Integer visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    @ApiModelProperty("菜单状态")
    private Integer status;

    /**
     * 权限标识
     */
    @ApiModelProperty("权限表示")
    private String perms;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
