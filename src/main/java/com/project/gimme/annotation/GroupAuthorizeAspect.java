package com.project.gimme.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author zxl
 */
@Aspect
@Component
@Slf4j
public class GroupAuthorizeAspect {

    public static final String TYPE_ADMIN = "admin";
    public static final String TYPE_USER = "user";
    public static final String TOKEN = "Token";

    /**
     * 目标方法
     */
    @Pointcut("@annotation(com.project.gimme.annotation.GroupAuthorize)")
    private void permission() {

    }

    /**
     * 环绕
     * 会将目标方法封装起来
     * 具体验证业务数据
     */
    @Around("permission()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /*
         * 获取当前http请求中的token
         * 解析Token :
         * 1、Token是否存在
         * 2、Token格式是否正确
         * 3、Token是否已过期（解析信息或者redis中是否存在）
         * */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(TOKEN);

        /*
         * 获取注解的值，并进行权限验证
         * */
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        GroupAuthorize authorize = method.getAnnotation(GroupAuthorize.class);
        Integer value = authorize.value().getCode();
        // 将注解的值和token解析后的值进行对比，查看是否有该权限，如果权限通过，允许访问方法；否则不允许，并抛出异常
        // 执行具体方法
        return proceedingJoinPoint.proceed();
    }

}
