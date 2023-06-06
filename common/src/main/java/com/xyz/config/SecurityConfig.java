package com.xyz.config;

import com.xyz.filter.JwtAuthenticationTokenFilter;
import com.xyz.handler.security.AccessDeniedHandlerImpl;
import com.xyz.handler.security.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security配置类
 * @author xyz
 * 2023/5/30
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    // 注入 AuthenticationManager bean对象
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    // 访问权限验证
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;
    // 登录权限验证
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    // Security5.7以后的权限设置
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 关闭 csrf
                .csrf().disable()
                // 不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/admin/login").anonymous()
                .antMatchers("/admin/register").anonymous()
                .antMatchers(SWAGGER_WHITELIST).anonymous()
                // 除上面外的所有请求全部需要鉴权验证
                .anyRequest().authenticated();

        // 配置自定义JWT过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置登录权限/访问权限验证
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
        // 关闭原有登录与注销界面
        http.logout().disable();
        http.formLogin().disable();

        return http.build();
    }
    // 修改默认加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * 放行Swagger
     */
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config",
            "/webjars/**",
            "/doc.html",
            "/test/**",
//            "/**",
    };
}
