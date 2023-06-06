package com.xyz.utils;

import com.xyz.domain.login.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security工具类
 */
public class SecurityUtils {

    public static LoginUser getLoginUser(){
        Authentication authentication = getAuthentication();
        Object principal = authentication.getPrincipal();


        return (LoginUser)principal;
    }

    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        Long id =getLoginUser().getUserEntity().getId();
        return id != null && 1L == id;
    }

    public static Long getUserId(){
        return getLoginUser().getUserEntity().getId();
    }
}
