package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:47
 */

@Data
@ApiModel(description = "信息通知实体类")
public class PersonalMsg {
    /**
     * 信息通知id
     */
    @ApiModelProperty(value = "信息通知id", name = "id", required = true)
    private Integer id;
    /**
     * 群聊/个人/频道类型
     */
    @ApiModelProperty(value = "消息通知类型", name = "type")
    private String objectType;
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
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "note")
    private String note;
    /**
     * 处理状态
     */
    @ApiModelProperty(value = "处理状态", name = "status")
    private Integer status;
    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型", name = "type")
    private String type;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳", name = "timestamp")
    private Date timestamp;
}
