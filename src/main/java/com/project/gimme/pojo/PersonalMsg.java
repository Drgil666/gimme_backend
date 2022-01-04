package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:47
 */

@Data
@ApiModel(description = "信息通知")
public class PersonalMsg {
    /**
     * 信息通知id
     */
    @ApiModelProperty(value = "信息通知id", name = "id", required = true)
    private Integer id;
    /**
     * 消息通知类型
     */
    @ApiModelProperty(value = "消息通知类型", name = "type")
    private Integer type;
    /**
     * 消息创建者id
     */
    @ApiModelProperty(value = "消息创建者id", name = "ownerId")
    private Integer ownerId;
    /**
     * 被操作者id
     */
    @ApiModelProperty(value = "被操作者id", name = "operatorId")
    private Integer operatorId;
    /**
     * 群聊/个人/频道id
     */
    @ApiModelProperty(value = "群聊/个人/频道id", name = "objectId")
    private Integer objectId;
}
