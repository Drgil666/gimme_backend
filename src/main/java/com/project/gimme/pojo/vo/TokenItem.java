package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/2/6 17:03
 */
@Data
@ApiModel(description = "token参数")
public class TokenItem {
    /**
     * 键
     */
    @ApiModelProperty(value = "键", name = "key")
    private String key;
    /**
     * 值
     */
    @ApiModelProperty(value = "值", name = "value")
    private String value;
}
