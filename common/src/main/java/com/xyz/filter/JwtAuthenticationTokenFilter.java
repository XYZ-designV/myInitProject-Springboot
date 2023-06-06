package com.xyz.filter;

import com.xyz.domain.login.entity.LoginUser;
import com.xyz.utils.JwtUtil;
import com.xyz.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * jwt认证过滤器
 * @author xyz
 * 2023/5/30
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        //获取请求头中的token
        String userId = null;
        String token = request.getHeader("token");
        //如果token不存在说明用户没有登录直接放行
        if (!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();

        } catch (Exception e) {
            e.printStackTrace();
            //token超时
            //token非法
            //响应告诉前端需要重新登录
//            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN.getCode(),"请重新登录");
//            WebUtils.renderString(response, JSON.toJSONString(result));
            throw new RuntimeException("用户未登录");
        }
        //解析获取userId
        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject("admin" + userId);
        if (Objects.isNull(loginUser)){
//            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN.getCode(),"请重新登录");
//            WebUtils.renderString(response, JSON.toJSONString(result));
            throw new RuntimeException("用户未登录2");
//            return;
        }
        //存入SecurityContext
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}