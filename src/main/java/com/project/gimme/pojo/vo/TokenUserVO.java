package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/2/6 23:12
 */
@Data
@ApiModel(description = "用户token")
public class TokenUserVO {
    /**
     * token类型
     */
    @ApiModelProperty(value = "token类型", name = "type")
    private String type;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;
}
