package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 10:02
 */
@Data
@ApiModel(description = "频道")
public class Channel {
    /**
     * 频道id
     */
    @ApiModelProperty(value = "频道id", name = "id", required = true)
    private Integer id;
    /**
     * 创建者id
     */
    @ApiModelProperty(value = "创建者id", name = "ownerId")
    private Integer ownerId;
    /**
     * 频道名
     */
    @ApiModelProperty(value = "频道名", name = "nick")
    private String nick;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;
    /**
     * 频道介绍
     */
    @ApiModelProperty(value = "群介绍", name = "description")
    private String description;
    /**
     * 频道头像
     */
    @ApiModelProperty(value = "频道头像", name = "avatar")
    private String avatar;
}
