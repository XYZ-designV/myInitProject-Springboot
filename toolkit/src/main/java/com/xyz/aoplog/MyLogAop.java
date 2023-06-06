package com.xyz.aoplog;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class MyLogAop {
    // 切点
    @Pointcut("@annotation(com.xyz.aoplog.AopLog)")
    public void aopLogAspect(){}

    // 前置通知
    @Before("aopLogAspect()")
    public void beforeLog(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 获取用户调用url地址，入参
        log.info("url路径：" + request.getRequestURI() + ",用户输入数据：" + Arrays.toString(joinPoint.getArgs()) );
    }

    // 后置通知
    @After("aopLogAspect()")
    public void afterAopLog(JoinPoint joinPoint) {
//        System.out.println("后置通知");
    }

    // 环绕通知
    @Around("aopLogAspect()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            System.out.println("前置通知");
            // 执行被注解的方法,有可能存在返回值
            result = joinPoint.proceed();
            System.out.println("后置通知");

            // 使用反射的方式动态的判断返回值数据
            if ( null != result) {
                Class<?> aClass = result.getClass();
                Field[] fields = aClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object o = field.get(result);
                    log.info("方法返回值为：" + o);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("自定义Log注解方法执行错误信息：" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
