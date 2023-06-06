package com.xyz.domain.menu.service;

import com.xyz.domain.menu.controller.MenuDTO;
import com.xyz.domain.menu.entity.MenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xyz.domain.common.ResponseResult;

/**
* @author 76596
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2023-05-24 15:22:19
*/
public interface MenuService extends IService<MenuEntity> {

    /**
     * 分页查询所有菜单列表
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return 分页菜单列表
     */
    ResponseResult allMenu(Integer pageNum, Integer pageSize);

    /**
     * 新增菜单
     * @param menuDTO 菜单数据
     * @return code200/msg操作成功
     */
    ResponseResult addMenu(MenuDTO menuDTO);

    /**
     * 修改菜单
     * @param menuDTO 修改数据
     * @return code200/msg操作成功
     */
    ResponseResult editMenu(MenuDTO menuDTO);

    /**
     * 根据id删除菜单
     * @param id 菜单id
     * @return code200/msg操作成功
     */
    ResponseResult deleteMenuById(Long id);


}
