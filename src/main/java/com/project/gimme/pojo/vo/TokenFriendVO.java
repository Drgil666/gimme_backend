package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/2/6 23:12
 */
@Data
@ApiModel(description = "好友token")
public class TokenFriendVO {
    /**
     * token类型
     */
    @ApiModelProperty(value = "token类型", name = "type")
    private String type;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳", name = "timestamp")
    private Date timestamp;
}
