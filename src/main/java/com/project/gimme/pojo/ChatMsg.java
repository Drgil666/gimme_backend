package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:48
 */
@Data
@ApiModel(description = "聊天信息")
public class ChatMsg {
    /**
     * 聊天信息id
     */
    @ApiModelProperty(value = "聊天信息id", name = "id", required = true)
    private Integer id;
    /**
     * 聊天内容
     */
    @ApiModelProperty(value = "聊天内容", name = "text")
    private String text;
    /**
     * 聊天消息类型
     */
    @ApiModelProperty(value = "聊天消息类型", name = "type")
    private String type;
    /**
     * 接受者id/群聊id/频道公告id
     */
    @ApiModelProperty(value = "接受者id/群聊id/频道公告id", name = "objectId", required = true)
    private Integer objectId;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳", name = "timestamp")
    private Date timeStamp;
    /**
     * 消息发起者
     */
    @ApiModelProperty(value = "消息发起者", name = "ownerId")
    private Integer ownerId;
}
