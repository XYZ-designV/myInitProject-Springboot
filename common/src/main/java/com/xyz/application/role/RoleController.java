package com.xyz.application.role;

import com.xyz.domain.common.ResponseResult;
import com.xyz.domain.role.controller.EditRoleDTO;
import com.xyz.domain.role.controller.IRole;
import com.xyz.domain.role.controller.AddRoleDTO;
import com.xyz.domain.role.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController implements IRole {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/all")
    @Override
    public ResponseResult all() {
        return roleService.all();
    }

    @PostMapping("/add")
    @Override
    public ResponseResult addRole(@RequestBody AddRoleDTO roleDTO) {
        // TODO 权限校验 - 参数校验
        return roleService.addRole(roleDTO);
    }

    @PutMapping("/edit")
    @Override
    public ResponseResult editRole(@RequestBody EditRoleDTO editRoleDTO) {
        // TODO 权限校验 - 参数校验
        return roleService.editRole(editRoleDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseResult deleteRoleById(@PathVariable("id") Long id) {
        return roleService.deleteRoleById(id);
    }
}
