package com.xyz.handler.security;

import com.alibaba.fastjson.JSON;
import com.xyz.constants.AppHttpCodeEnum;
import com.xyz.domain.common.ResponseResult;
import com.xyz.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security登录认证失败处理器
 * @author xyz
 * 2023/5/30
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        authException.printStackTrace();
        ResponseResult result = null;
        if (authException instanceof BadCredentialsException) {
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        } else if (authException instanceof InsufficientAuthenticationException) {
            // 未登录状态返回
            result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        else {
            result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR.getCode(),"用户登录或认证失败！");
        }
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
