package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 10:58
 */
@Data
@ApiModel(description = "频道公告")
public class ChannelNotice {
    /**
     * 频道公告id
     */
    @ApiModelProperty(value = "频道公告id", name = "id", required = true)
    private Integer id;
    /**
     * 频道公告类型
     */
    @ApiModelProperty(value = "频道公告类型", name = "type")
    private String type;
    /**
     * 频道id
     */
    @ApiModelProperty(value = "频道id", name = "channelId")
    private Integer channelId;
    /**
     * 频道公告内容
     */
    @ApiModelProperty(value = "频道公告内容", name = "text")
    private String text;
    /**
     * 频道公告创建时间
     */
    @ApiModelProperty(value = "频道公告创建时间", name = "createTime")
    private Date createTime;
}
