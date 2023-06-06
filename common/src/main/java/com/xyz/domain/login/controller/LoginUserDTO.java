package com.xyz.domain.login.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "UserDTO - 用户登录信息",description = "用户登录信息")
public class LoginUserDTO implements Serializable {

    private static final long serialVersionUID = -5829711301114201114L;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String userName;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 5 ,max = 20 ,message = "用户密码应该大于6位")
    @ApiModelProperty("用户密码")
    private String password;
}
