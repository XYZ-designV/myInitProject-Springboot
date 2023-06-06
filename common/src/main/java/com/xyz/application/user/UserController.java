package com.xyz.application.user;

import com.xyz.aoplog.AopLog;
import com.xyz.domain.user.controller.*;
import com.xyz.domain.user.service.impl.UserServiceImpl;
import com.xyz.domain.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController implements IUser {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/queries")
    @Override
    @AopLog
    public ResponseResult queryUsers(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     String userName,
                                     String nickName,
                                     String phoneNumber,
                                     Long id) {

        return userService.queryUsers(pageNum, pageSize, userName, nickName, phoneNumber, id);
    }

    @PostMapping("/add")
    @Override
    @AopLog
    public ResponseResult addUser(@RequestBody @Validated AddUserDTO addUserDTO) {
        return userService.addUser(addUserDTO);
    }

    @PostMapping("/edit")
    @Override
    @AopLog
    public ResponseResult editUser(@RequestBody @Validated EditUserDTO editUserDTO) {
        return userService.editUser(editUserDTO);
    }

    @PutMapping("/edit")
    @Override
    @AopLog
    public ResponseResult editPwd(@RequestBody @Validated EditPwdDTO editPwdDTO) {
        return userService.editPwd(editPwdDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    @AopLog
    public ResponseResult deleteUserById(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }
}
