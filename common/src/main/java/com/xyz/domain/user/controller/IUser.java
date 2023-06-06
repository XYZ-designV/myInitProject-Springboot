package com.xyz.domain.user.controller;


import com.xyz.domain.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户表相关接口")
public interface IUser {

    @ApiOperation(value = "条件查询用户信息", notes = "条件查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true),
            @ApiImplicitParam(name = "userName", value = "用户名称"),
            @ApiImplicitParam(name = "nickName", value = "用户昵称"),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号"),
            @ApiImplicitParam(name = "id", value = "用户id")
    })
    ResponseResult queryUsers(Integer pageNum, Integer pageSize, String userName, String nickName,String phoneNumber,Long id);

    @ApiOperation(value = "新增用户", notes = "新增用户")
    ResponseResult addUser(AddUserDTO addUserDTO);

    @ApiOperation(value = "修改用户信息", notes = "修改用户")
    ResponseResult editUser(EditUserDTO editUserDTO);

    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    ResponseResult editPwd(EditPwdDTO editPwdDTO);

    @ApiOperation(value = "根据id删除用户信息", notes = "根据id删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    ResponseResult deleteUserById(Long id);

}
