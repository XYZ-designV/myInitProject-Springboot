package com.xyz.domain.role.service;

import com.xyz.domain.common.ResponseResult;
import com.xyz.domain.role.controller.AddRoleDTO;
import com.xyz.domain.role.controller.EditRoleDTO;
import com.xyz.domain.role.entity.RoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 76596
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2023-05-24 15:22:19
*/
public interface RoleService extends IService<RoleEntity> {
    /**
     * 查询所有角色信息
     * @return 角色信息列表
     */
    ResponseResult all();

    /**
     * 新增角色信息
     * @param addRoleDTO 角色信息
     * @return code200/msg操作成功
     */
    ResponseResult addRole(AddRoleDTO addRoleDTO);

    /**
     * 修改角色信息
     * @param editRoleDTO 角色信息
     * @return code200/msg操作成功
     */
    ResponseResult editRole(EditRoleDTO editRoleDTO);

    /**
     * 根据角色Id删除角色信息
     * @param id 角色Id
     * @return code200/msg操作成功
     */
    ResponseResult deleteRoleById(Long id);
}
