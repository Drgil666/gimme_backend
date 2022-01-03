package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2020/9/24 16:33
 */
@Data
@AllArgsConstructor
@ApiModel(description = "Response")
public class Response<T> {
    @ApiModelProperty(value = "错误码", name = "code", required = true)
    private Integer code;
    @ApiModelProperty(value = "错误信息", name = "msg", required = true)
    private String msg;
    @ApiModelProperty(value = "数据", name = "data", required = true)
    private T data;

    public static <T> Response<T> createSuc(T o) {
        return new Response<T>(200, null, o);
    }

    public static <T> Response<T> createErr(String msg) {
        return new Response<T>(-1, msg, null);
    }

    public static <T> Response<T> createErr(int code, String msg) {
        return new Response<T>(code, msg, null);
    }

    public static <T> Response<T> createTokenAuthorizedErr() {
        return new Response<>(5, "Token失效或不存在!", null);
    }
}
