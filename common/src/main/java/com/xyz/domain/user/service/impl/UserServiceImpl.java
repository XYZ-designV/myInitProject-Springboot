package com.xyz.domain.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.domain.common.PageResponse;
import com.xyz.domain.user.controller.*;
import com.xyz.domain.user.entity.UserEntity;
import com.xyz.domain.user.mapper.UserMapper;
import com.xyz.domain.user.service.UserService;
import com.xyz.utils.BeanCopyUtils;
import com.xyz.domain.common.ResponseResult;
import io.jsonwebtoken.lang.Strings;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* @author 76596
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-05-24 15:22:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
    implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<String> getAdminPermissionByUserId(Long id) {
        return userMapper.getAdminPermissionByUserId(id);
    }

    @Override
    public ResponseResult queryUsers(Integer pageNum, Integer pageSize, String userName, String nickName,String phoneNumber, Long id) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(null != id,UserEntity::getId,id);
        queryWrapper.like(Strings.hasText(userName),UserEntity::getUserName,userName);
        queryWrapper.like(Strings.hasText(nickName),UserEntity::getNickName,nickName);
        queryWrapper.like(Strings.hasText(phoneNumber),UserEntity::getPhoneNumber,phoneNumber);
        Page<UserEntity> page = new Page<>(pageNum,pageSize);
        page(page, queryWrapper);

        if (CollectionUtils.isEmpty(page.getRecords())) {
            return ResponseResult.okResult(200,"未搜索到满足条件的数据!!!");
        }
        List<UserVO> userVOS = BeanCopyUtils.copyBeanList(page.getRecords(), UserVO.class);
        return ResponseResult.okResult(new PageResponse<>(userVOS,page.getTotal()));
    }

    @Override
    public ResponseResult addUser(AddUserDTO addUserDTO) {
        // 密码加密
        String encode = passwordEncoder.encode(addUserDTO.getPassword());
        addUserDTO.setPassword(encode);
        // 封装用户信息 addUserDTO -> userEntity
        UserEntity userEntity = BeanCopyUtils.copyBean(addUserDTO, UserEntity.class);
        save(userEntity);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editUser(EditUserDTO editUserDTO) {
        UserEntity userEntity = BeanCopyUtils.copyBean(editUserDTO, UserEntity.class);
        updateById(userEntity);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editPwd(EditPwdDTO editPwdDTO) {
        String encode = passwordEncoder.encode(editPwdDTO.getPassword());
        LambdaUpdateWrapper<UserEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserEntity::getId,editPwdDTO.getId());
        updateWrapper.set(UserEntity::getPassword,encode);
        update(null,updateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteUserById(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

}




