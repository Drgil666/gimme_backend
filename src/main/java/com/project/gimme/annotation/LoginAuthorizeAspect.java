package com.project.gimme.annotation;

import com.project.gimme.exception.ErrorCode;
import com.project.gimme.exception.ErrorException;
import com.project.gimme.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.project.gimme.utils.RedisUtil.TOKEN;

/**
 * @author zxl
 */
@Aspect
@Component
@Slf4j
public class LoginAuthorizeAspect {
    @Resource
    private RedisService redisService;


    /**
     * 环绕
     * 会将目标方法封装起来
     * 具体验证业务数据
     */
    @Around(value = "@annotation(com.project.gimme.annotation.LoginAuthorize)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(TOKEN);
        if (redisService.checkUserLoginToken(token)) {
            return proceedingJoinPoint.proceed();
        } else {
            throw new ErrorException(ErrorCode.TOKEN_ILLEGAL);
        }
    }

}
