package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/2/6 16:55
 */
@Data
@ApiModel(description = "token实体类")
public class TokenVO {
    /**
     * token类型
     */
    @ApiModelProperty(value = "token类型", name = "tokenType")
    private String tokenType;
    /**
     * 参数列表
     */
    @ApiModelProperty(value = "参数列表", name = "itemList")
    private List<TokenItem> itemList;
}
