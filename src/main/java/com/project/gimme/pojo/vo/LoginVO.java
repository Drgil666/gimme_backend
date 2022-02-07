package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/2/7 13:47
 */
@Data
@ApiModel(description = "登录中间类")
public class LoginVO {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;
}
