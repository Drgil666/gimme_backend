package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:21
 */
@Data
@ApiModel(description = "频道成员")
public class ChannelUser {
    /**
     * 频道id
     */
    @ApiModelProperty(value = "频道id", name = "channelId", required = true)
    private Integer channelId;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    private Integer userId;
    /**
     * 用户权限类型
     */
    @ApiModelProperty(value = "权限类型", name = "type")
    private Integer type;
    /**
     * 频道昵称
     */
    @ApiModelProperty(value = "频道昵称", name = "channelNick")
    private String channelNick;
}
