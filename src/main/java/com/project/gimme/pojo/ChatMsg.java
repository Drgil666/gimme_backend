package com.project.gimme.pojo;

import com.project.gimme.utils.TEAUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Base64;
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
     * 会话类型
     */
    @ApiModelProperty(value = "会话类型", name = "type")
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
    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型", name = "msgType")
    private Integer msgType;
    private transient Boolean isTea = false;

    public void encode() {
        if (isTea) {
            byte[] bytes = TEAUtil.encryptByTea(text);
            text = Base64.getEncoder().encodeToString(bytes);
        }
    }

    public void decode() {
        if (isTea) {
            byte[] bytes = Base64.getDecoder().decode(text);
            text = TEAUtil.decryptByTea(bytes);
        }
    }
}
