package com.project.gimme.annotation;

import com.project.gimme.exception.ErrorCode;
import com.project.gimme.exception.ErrorException;
import com.project.gimme.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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
        //检查token是否存在
        Enumeration<String> headerNames = request.getHeaderNames();
        boolean tokenExist = false;
        while (headerNames.hasMoreElements()) {
            String headName = headerNames.nextElement();
            if (headName.equals(TOKEN)) {
                tokenExist = true;
                break;
            }
        }
        if (!tokenExist) {
            throw new ErrorException(ErrorCode.TOKEN_ILLEGAL, "token不存在!");
        }
        String token = request.getHeader(TOKEN);
        if (!StringUtils.isEmpty(token) && redisService.checkUserLoginToken(token)) {
            return proceedingJoinPoint.proceed();
        } else {
            throw new ErrorException(ErrorCode.TOKEN_ILLEGAL);
        }
    }

}
