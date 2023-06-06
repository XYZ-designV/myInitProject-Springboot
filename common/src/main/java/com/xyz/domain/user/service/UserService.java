package com.xyz.domain.user.service;

import com.xyz.domain.user.controller.AddUserDTO;
import com.xyz.domain.user.controller.EditPwdDTO;
import com.xyz.domain.user.controller.EditUserDTO;
import com.xyz.domain.user.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xyz.domain.common.ResponseResult;

import java.util.List;


public interface UserService extends IService<UserEntity> {

    List<String> getAdminPermissionByUserId(Long id);

    /**
     * 分页查询用户信息列表
     * 可使用userName，nicKName,phoneNumber条件模糊查询
     * 若不传查询条件则查询所有
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @param userName 用户名
     * @param nickName 用户昵称
     * @param phoneNumber 手机号
     * @param id 用户id
     * @return 用户信息列表
     */
    ResponseResult queryUsers(Integer pageNum, Integer pageSize, String userName, String nickName,String phoneNumber,Long id);

    /**
     * 新增用户
     * @param addUserDTO 新增用户信息
     * @return code200/msg操作成功
     */
    ResponseResult addUser(AddUserDTO addUserDTO);

    /**
     * 修改用户
     * @param editUserDTO 修改用户信息
     * @return code200/msg操作成功
     */
    ResponseResult editUser(EditUserDTO editUserDTO);

    /**
     * 根据用户Id修改密码
     * @param editPwdDTO 用户密码信息
     * @return code200/msg操作成功
     */
    ResponseResult editPwd(EditPwdDTO editPwdDTO);

    /**
     * 根据用户Id删除用户信息 -- 伪删除
     * @param id 用户Id
     * @return code200/msg操作成功
     */
    ResponseResult deleteUserById(Long id);
}
