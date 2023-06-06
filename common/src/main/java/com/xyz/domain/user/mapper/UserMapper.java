package com.xyz.domain.user.mapper;

import com.xyz.domain.user.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author 76596
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2023-05-24 15:22:19
* @Entity com.xyz.domain.user.entity.UserEntity
*/
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    List<String> getAdminPermissionByUserId(Long id);
}




