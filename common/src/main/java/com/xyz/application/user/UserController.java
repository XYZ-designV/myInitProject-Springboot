package com.xyz.application.user;

import com.xyz.domain.user.controller.*;
import com.xyz.domain.user.service.impl.UserServiceImpl;
import com.xyz.domain.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController implements IUser {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/queries")
    @Override
    public ResponseResult queryUsers( Integer pageNum,
                                      Integer pageSize,
                                      String userName,
                                      String nickName,
                                      String phoneNumber,
                                      Long id) {

        return userService.queryUsers(pageNum, pageSize, userName, nickName, phoneNumber, id);
    }

    @PostMapping("/add")
    @Override
    public ResponseResult addUser(@RequestBody AddUserDTO addUserDTO) {
        return userService.addUser(addUserDTO);
    }

    @PostMapping("/edit")
    @Override
    public ResponseResult editUser(@RequestBody EditUserDTO editUserDTO) {
        return userService.editUser(editUserDTO);
    }

    @PutMapping("/edit")
    @Override
    public ResponseResult editPwd(@RequestBody EditPwdDTO editPwdDTO) {
        return userService.editPwd(editPwdDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseResult deleteUserById(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }
}
