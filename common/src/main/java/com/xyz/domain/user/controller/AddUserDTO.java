package com.xyz.domain.user.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "AddUserDTO - 用户新增数据", description = "用户新增数据")
@Data
public class AddUserDTO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String userName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = true)
    private String nickName;

        /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 用户类型：0代表普通用户，1代表管理员
     */
    @ApiModelProperty(value = "用户类型")
    private Integer type;

    /**
     * 账号状态（0正常 1停用）
     */
    @ApiModelProperty(value = "账号状态")
    private Integer status;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    /**
     * 用户性别（0男，1女，2未知）
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

}
