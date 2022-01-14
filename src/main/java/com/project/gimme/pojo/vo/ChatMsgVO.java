package com.project.gimme.pojo.vo;

import com.project.gimme.pojo.ChatMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/1/14 13:50
 */
@Data
@ApiModel(description = "聊天消息中间类")
public class ChatMsgVO extends ChatMsg {
    /**
     * 是否是自己
     */
    @ApiModelProperty(value = "是否是自己", name = "isSelf")
    private Boolean isSelf;
}
