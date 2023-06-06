package com.xyz.domain.login.controller;


import com.xyz.domain.common.ResponseResult;
import com.xyz.domain.user.controller.AddUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户登录相关接口")
public interface ILogin {
    @ApiOperation(value = "登录接口",notes = "用户登录")
    ResponseResult login(LoginUserDTO userDTO);

    @ApiOperation(value = "注销接口", notes = "退出登录")
    ResponseResult logout();

    @ApiOperation(value = "注册用户接口", notes = "注册用户接口")
    ResponseResult userRegister(AddUserDTO addUserDTO);
}
