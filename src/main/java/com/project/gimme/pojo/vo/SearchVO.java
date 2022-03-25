package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2022/3/23 12:30
 */
@Data
@ApiModel(description = "搜索结果中间类")
public class SearchVO {
    /**
     * 会话头像
     */
    @ApiModelProperty(value = "会话头像", name = "avatar")
    private String avatar;
    /**
     * 会话名
     */
    @ApiModelProperty(value = "会话名", name = "objectNick")
    private String objectNick;
    /**
     * 会话id
     */
    @ApiModelProperty(value = "会话id", name = "objectId")
    private Integer objectId;
    /**
     * 总人数
     */
    @ApiModelProperty(value = "总人数", name = "memberCount")
    private Integer memberCount;
    /**
     * 是否建立关系
     */
    @ApiModelProperty(value = "是否建立关系", name = "isJoined")
    private Boolean isJoined;
    /**
     * 会话类型
     */
    @ApiModelProperty(value = "会话类型", name = "objectType")
    private String objectType;
}
