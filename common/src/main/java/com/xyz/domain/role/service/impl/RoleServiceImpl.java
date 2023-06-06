package com.xyz.domain.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.domain.common.ResponseResult;
import com.xyz.domain.role.controller.AddRoleDTO;
import com.xyz.domain.role.controller.EditRoleDTO;
import com.xyz.domain.role.controller.RoleVO;
import com.xyz.domain.role.entity.RoleEntity;
import com.xyz.domain.role.mapper.RoleMapper;
import com.xyz.domain.role.service.RoleService;
import com.xyz.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 76596
* @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
* @createDate 2023-05-24 15:22:19
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity>
    implements RoleService {

    @Override
    public ResponseResult all() {
        List<RoleEntity> roleEntities = list();
        List<RoleVO> roleVos = BeanCopyUtils.copyBeanList(roleEntities, RoleVO.class);
        return ResponseResult.okResult(roleVos);
    }

    @Override
    public ResponseResult addRole(AddRoleDTO roleDTO) {
        RoleEntity roleEntity = BeanCopyUtils.copyBean(roleDTO, RoleEntity.class);
        save(roleEntity);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editRole(EditRoleDTO editRoleDTO) {
        RoleEntity roleEntity = BeanCopyUtils.copyBean(editRoleDTO, RoleEntity.class);
        updateById(roleEntity);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRoleById(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}




