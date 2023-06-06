package com.xyz.domain.userrole.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.domain.userrole.entity.UserRoleEntity;
import com.xyz.domain.userrole.mapper.UserRoleMapper;
import com.xyz.domain.userrole.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
* @author 76596
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
* @createDate 2023-05-24 15:22:19
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity>
    implements UserRoleService {

}




