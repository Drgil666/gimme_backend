package com.project.gimme.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author DrGilbert
 */

@AllArgsConstructor
@Getter
public enum ErrorCode {
    /**
     * 参数非法
     */
    UNKNOWN_ERROR(1, "未知错误", LogLevel.ERROR),
    SYSTEM_ERROR(2, "系统错误", LogLevel.ERROR),
    BIZ_PARAM_ILLEGAL(3, "外部参数错误", LogLevel.INFO),
    INNER_PARAM_ILLEGAL(4, "内部参数错误", LogLevel.ERROR),
    TOKEN_AUTHORIZE_ILLEGAL(5, "授权错误,请重新登录!", LogLevel.ERROR);
    private final Integer code;
    private final String msg;
    private final LogLevel logLevel;
}
