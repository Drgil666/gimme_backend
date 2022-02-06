package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/2/6 23:12
 */
@Data
@ApiModel(description = "好友token")
public class TokenGroupVO {
    /**
     * token类型
     */
    @ApiModelProperty(value = "token类型", name = "type")
    private String type;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;
    /**
     * 群聊id
     */
    @ApiModelProperty(value = "群聊id", name = "groupId")
    private Integer groupId;
    /**
     * 成员权限
     */
    @ApiModelProperty(value = "成员权限", name = "memberType")
    private String memberType;
}
