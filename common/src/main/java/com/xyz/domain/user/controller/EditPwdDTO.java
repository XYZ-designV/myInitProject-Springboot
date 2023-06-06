package com.xyz.domain.user.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "EditPwdDTO - 修改密码数据", description = "修改密码数据")
@Data
public class EditPwdDTO {
    /**
     * 主键
     */
    @ApiModelProperty(value = "用户主键Id", required = true)
    private Long id;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;

}
