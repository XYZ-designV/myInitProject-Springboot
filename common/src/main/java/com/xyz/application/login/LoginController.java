package com.xyz.application.login;

import com.xyz.aoplog.AopLog;
import com.xyz.domain.login.controller.ILogin;
import com.xyz.domain.login.controller.LoginUserDTO;
import com.xyz.domain.login.service.impl.LoginServiceImpl;
import com.xyz.domain.common.ResponseResult;
import com.xyz.domain.user.controller.AddUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
public class LoginController implements ILogin {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/login")
    @Override
    @AopLog
    public ResponseResult login(@Validated @RequestBody LoginUserDTO userDTO) {
        return loginService.login(userDTO);
    }

    @PostMapping("/logout")
    @Override
    @AopLog
    public ResponseResult logout() {
        return loginService.logout();
    }

    @PostMapping("/register")
    @Override
    @AopLog
    public ResponseResult userRegister(@Validated @RequestBody AddUserDTO addUserDTO) {

        return loginService.userRegister(addUserDTO);
    }

}
