package com.xyz.domain.role.controller;

import com.xyz.domain.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@Api(tags = "角色权限表相关接口")
public interface IRole {

    @ApiOperation(value = "查询所有权限", notes = "查询所有权限")
    ResponseResult all();

    @ApiOperation(value = "新增权限信息", notes = "新增权限信息")
    ResponseResult addRole(AddRoleDTO roleDTO);

    @ApiOperation(value = "修改权限信息", notes = "修改权限信息")
    ResponseResult editRole(EditRoleDTO editRoleDTO);

    @ApiOperation(value = "根据权限Id删除权限信息", notes = "根据权限Id删除权限信息")
    @ApiModelProperty(name = "id", value = "权限id", required = true)
    ResponseResult deleteRoleById(Long id);
}
