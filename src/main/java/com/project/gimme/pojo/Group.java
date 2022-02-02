package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/3 10:28
 */
@Data
@ApiModel(description = "群聊实体类")
public class Group {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "群聊id", name = "id", required = true)
    private Integer id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "用户id", name = "id", required = true)
    private Date createTime;
    /**
     * 群聊昵称
     */
    @ApiModelProperty(value = "用户id", name = "id", required = true)
    private String nick;
    /**
     * 群描述
     */
    @ApiModelProperty(value = "群描述", name = "description")
    private String description;
    /**
     * 群头像
     */
    @ApiModelProperty(value = "群头像", name = "avatar")
    private String avatar;
}
