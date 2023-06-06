package com.xyz.domain.login.service.impl;

import com.xyz.application.user.UserController;
import com.xyz.domain.login.controller.LoginUserDTO;
import com.xyz.domain.login.entity.LoginUser;
import com.xyz.domain.login.service.LoginService;
import com.xyz.domain.user.controller.AddUserDTO;
import com.xyz.utils.JwtUtil;
import com.xyz.utils.RedisCache;
import com.xyz.domain.common.ResponseResult;
import com.xyz.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserController userController;

    @Override
    public ResponseResult login(LoginUserDTO userDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取userId 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUserEntity().getId().toString();
        String token = JwtUtil.createJWT(userId);
        // 将用户信息存入redis
        redisCache.setCacheObject("admin" + userId, loginUser);
        return ResponseResult.okResult(token);
    }

    @Override
    public ResponseResult logout() {
        // 获取用户id，使用id删除redis中的用户数据
        Long userId = SecurityUtils.getUserId();
        redisCache.deleteObject("admin" + userId);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userRegister(AddUserDTO addUserDTO) {
        return userController.addUser(addUserDTO);
    }

}
