package com.xyz.handler.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xyz.domain.login.entity.LoginUser;
import com.xyz.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis自动填充createTime、createBy、updateTime、updateBy
 * @author xyz
 * 2023/5/30
 */
@Component
@Slf4j
public class MyMeta0bjectHandler implements MetaObjectHandler {

    private Long userid;

    //插入时填充
    @Override
    public void insertFill(MetaObject metaObject) {


        try {
            this.userid = SecurityUtils.getUserId();
        }catch(Exception e){
//            e.printStackTrace();
            this.userid = -1L; //表示是自己创建
        }

        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("createBy",this.userid,metaObject);
        this.setFieldValByName("updateBy",this.userid,metaObject);

    }
    //更新时填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("updateBy", this.userid, metaObject);
    }

//    private Long getUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        Long userId = null;
//        try {
//            userId = loginUser.getUserEntity().getId();
//        } catch (Exception e) {
//            e.printStackTrace();
//            userId = -1L;
//        }
//        return userId;
//    }
}