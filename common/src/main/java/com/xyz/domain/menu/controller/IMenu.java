package com.xyz.domain.menu.controller;


import com.xyz.domain.common.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "菜单(权限)表相关接口" )
public interface IMenu {
    @ApiOperation(value = "查询所有菜单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", value = "页号", required = true),
        @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true),
    })
    ResponseResult allMenu(Integer pageNum, Integer pageSize);

    @ApiOperation(value = "新增菜单")
    ResponseResult addMenu(MenuDTO menuDTO);

    @ApiOperation(value = "修改菜单")
    ResponseResult editMenu(MenuDTO menuDTO);

    @ApiOperation(value = "根据Id删除菜单")
    @ApiImplicitParam(name = "id", value = "用户Id", required = true)
    ResponseResult deleteMenuById(Long id);

}
