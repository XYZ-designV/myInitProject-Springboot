package com.xyz.domain.login.service;

import com.xyz.domain.login.controller.LoginUserDTO;
import com.xyz.domain.common.ResponseResult;
import com.xyz.domain.user.controller.AddUserDTO;

public interface LoginService {
    /**
     * 用户登录
     * @param userDTO 用户登录信息
     * @return code200/msg操作成功
     */
    ResponseResult login(LoginUserDTO userDTO);

    /**
     * 注销用户
     * @return code200/msg操作成功
     */
    ResponseResult logout();

    /**
     * 注册用户
     * @param addUserDTO 用户数据
     * @return code200/msg操作成功
     */
    ResponseResult userRegister(AddUserDTO addUserDTO);
}
