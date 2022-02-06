package com.project.gimme.exception;

import cn.yueshutong.springbootstartercurrentlimiting.handler.CurrentInterceptorHandler;
import com.project.gimme.pojo.vo.Response;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author DrGilbert
 * @date 2021/4/2 21:17
 * 限流qps用
 */
@Component
public class CurrentLimitHandler implements CurrentInterceptorHandler {
    @Override
    public void preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.getWriter().print(Response.createErr("系统繁忙！请稍后再试！"));
    }
}
