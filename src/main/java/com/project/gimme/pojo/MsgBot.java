package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:26
 */
@Data
@ApiModel(description = "机器人")
public class MsgBot {
    /**
     * 机器人id
     */
    @ApiModelProperty(value = "机器人id", name = "id", required = true)
    private Integer id;
    /**
     * 机器人广播消息
     */
    @ApiModelProperty(value = "机器人广播消息", name = "text")
    private String text;
    /**
     * 机器人广播时间
     */
    @ApiModelProperty(value = "机器人广播时间", name = "date")
    private Date date;
    /**
     * 群聊id
     */
    @ApiModelProperty(value = "群聊id", name = "groupId")
    private Integer groupId;
    /**
     * 执行时间频率
     */
    @ApiModelProperty(value = "执行时间频率", name = "cron")
    private String cron;
}
