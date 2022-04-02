package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2022/3/14 10:27
 */
@Data
@ApiModel(description = "更新信息获取时间时的中间类")
public class RefreshVO {
    /**
     * 会话id
     */
    @ApiModelProperty(value = "会话id", name = "objectId")
    private Integer objectId;
    /**
     * 聊天类型
     */
    @ApiModelProperty(value = "聊天类型", name = "chatType")
    private String chatType;
    /**
     * 聊天记录id
     */
    @ApiModelProperty(value = "聊天记录id", name = "chatMsgId")
    private Integer chatMsgId;
}
