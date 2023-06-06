package com.xyz.application.login;


import com.xyz.aoplog.AopLog;
import com.xyz.domain.login.controller.ILogin;
import com.xyz.domain.login.controller.LoginUserDTO;
import com.xyz.domain.login.service.impl.LoginServiceImpl;
import com.xyz.domain.common.ResponseResult;
import com.xyz.domain.user.controller.AddUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @Validated
    public ResponseResult login(@Validated @RequestBody LoginUserDTO userDTO) {

        // 第一种：参数校验方式
//        StringBuilder stringBuilder = new StringBuilder();
//        String userName = userDTO.getUserName();
//        if (!StringUtils.hasText(userName)) {
//            stringBuilder.append("用户名不能为空;");
//        }
//        String password = userDTO.getPassword();
//        int pl = password.length();
//        if (!StringUtils.hasText(password)) {
//            stringBuilder.append("用户密码不能为空;");
//        }
//        if ( pl < 6 || pl > 150 ) {
//            stringBuilder.append("用户密码应该大于或等于6位;");
//        }
//        if (StringUtils.hasText(stringBuilder.toString())){
//            return ResponseResult.errorResult(500, stringBuilder.toString());
//        } else  {
//            return loginService.login(userDTO);
//        }
        // 第二种：参数校验方式
        return loginService.login(userDTO);
    }

    @PostMapping("/logout")
    @Override
    public ResponseResult logout() {
        return loginService.logout();
    }

    @PostMapping("/register")
    @Override
    public ResponseResult userRegister(@RequestBody AddUserDTO addUserDTO) {

        return loginService.userRegister(addUserDTO);
    }

}
