package com.xyz.domain.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xyz.constants.SystemConstants;
import com.xyz.domain.menu.entity.MenuEntity;
import com.xyz.domain.menu.service.impl.MenuServiceImpl;
import com.xyz.domain.user.entity.UserEntity;
import com.xyz.domain.login.entity.LoginUser;
import com.xyz.domain.menu.service.MenuService;
import com.xyz.domain.user.mapper.UserMapper;
import com.xyz.domain.user.service.UserService;
import com.xyz.domain.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LoginDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MenuServiceImpl menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询数据库用户信息
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUserName,username);
        UserEntity userEntity = userService.getOne(queryWrapper);
        // 校验是否查询到用户，如果没查询到即抛出异常
        if (Objects.isNull(userEntity)) {
            throw new RuntimeException("用户不存在");
        }
        // 权限封装
        List<String> permission;
        if (userEntity.getType().equals(SystemConstants.ADMIN)) {
            // 如果用户是后台管理员，则直接返回所有权限信息
            if (userEntity.getId().equals(SystemConstants.ROOT_ID)) {
                List<MenuEntity> list = menuService.list();
                permission = list.stream().map(MenuEntity::getPerms).collect(Collectors.toList());
            } else  { // 若不是，则返回根据用户ID查询所拥有的权限
                permission = userService.getAdminPermissionByUserId(userEntity.getId());
            }
            return new LoginUser(userEntity,permission);
        }
        // 普通用户
        return new LoginUser(userEntity);

    }
}
