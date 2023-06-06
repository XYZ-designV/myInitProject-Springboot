package com.xyz.domain.login.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "UserDTO - 用户登录信息",description = "用户登录信息")
public class LoginUserDTO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String userName;

    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6 ,max = 20 ,message = "用户密码应该大于或等于6位,且小于等于20位")
    @ApiModelProperty("用户密码")
    private String password;
}
