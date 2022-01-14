package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/11 14:08
 */
@Data
@ApiModel(description = "用户消息记录中间类")
public class MessageVO {
    /**
     * 用户/群聊/频道id
     */
    @ApiModelProperty(value = "用户id/群聊id/频道id", name = "id", required = true)
    private Integer objectId;
    /**
     * 用户/群聊/频道昵称
     */
    @ApiModelProperty(value = "用户/群聊/频道昵称", name = "nick", required = true)
    private String nick;
    /**
     * 用户/群聊/频道头像的mongoId
     */
    @ApiModelProperty(value = "用户/群聊/频道头像的mongoId", name = "avatar")
    private String avatar;
    /**
     * 最后一句聊天记录
     */
    @ApiModelProperty(value = "最后一句聊天的记录", name = "text")
    private String text;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳", name = "timestamp")
    private Date timestamp;

    /**
     * 聊天类型
     */
    @ApiModelProperty(value = "聊天类型", name = "type")
    private Integer type;
}
