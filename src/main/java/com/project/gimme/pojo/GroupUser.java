package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/1/3 10:47
 */
@Data
@ApiModel(description = "群聊成员实体类")
public class GroupUser {
    /**
     * 群聊id
     */
    @ApiModelProperty(value = "群聊id", name = "groupId", required = true)
    private Integer groupId;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    private Integer userId;
    /**
     * 用户权限类型
     */
    @ApiModelProperty(value = "权限类型", name = "type", required = true)
    private Integer type;
}
