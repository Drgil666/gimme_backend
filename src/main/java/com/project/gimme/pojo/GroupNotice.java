package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/1/4 9:34
 */
@Data
@ApiModel(description = "群公告")
public class GroupNotice {
    /**
     * 群公告id
     */
    @ApiModelProperty(value = "群公告id", name = "id", required = true)
    private Integer id;
    /**
     * 发布者id
     */
    @ApiModelProperty(value = "发布者id", name = "ownerId")
    private Integer ownerId;
    /**
     * 公告所在群聊id
     */
    @ApiModelProperty(value = "公告所在群聊id", name = "groupId")
    private Integer groupId;
    /**
     * 公告内容
     */
    @ApiModelProperty(value = "公告内容", name = "text")
    private String text;
}
