package com.project.gimme.exception;

import com.project.gimme.pojo.vo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author DrGilbert
 * @date 2021/4/1 14:40
 * 异常抛出转移为response类
 */
@ControllerAdvice
public class ErrorExceptionHandler {
    @ExceptionHandler(ErrorException.class)
    @ResponseBody
    public Response<String> resultError(ErrorException e) {
        return Response.createErr(e.getCode().getCode(), e.getMessage());
    }
}
