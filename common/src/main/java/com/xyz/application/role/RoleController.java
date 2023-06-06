package com.xyz.application.role;

import com.xyz.aoplog.AopLog;
import com.xyz.domain.common.ResponseResult;
import com.xyz.domain.role.controller.EditRoleDTO;
import com.xyz.domain.role.controller.IRole;
import com.xyz.domain.role.controller.AddRoleDTO;
import com.xyz.domain.role.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController implements IRole {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/all")
    @Override
    @AopLog
    public ResponseResult all() {
        return roleService.all();
    }

    @PostMapping("/add")
    @Override
    @AopLog
    public ResponseResult addRole(@RequestBody @Validated AddRoleDTO roleDTO) {
        return roleService.addRole(roleDTO);
    }

    @PutMapping("/edit")
    @Override
    @AopLog
    public ResponseResult editRole(@RequestBody @Validated EditRoleDTO editRoleDTO) {
        return roleService.editRole(editRoleDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    @AopLog
    public ResponseResult deleteRoleById(@PathVariable("id") Long id) {
        return roleService.deleteRoleById(id);
    }
}
