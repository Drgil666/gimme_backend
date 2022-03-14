package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/11 14:15
 */
@Data
@ApiModel(description = "好友信息实体类")
public class Friend {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;
    /**
     * 好友id
     */
    @ApiModelProperty(value = "好友id", name = "friendId")
    private Integer friendId;
    /**
     * 好友名备注
     */
    @ApiModelProperty(value = "好友名备注", name = "friendNote")
    private String friendNote;
    /**
     * 最后获取信息的时间
     */
    @ApiModelProperty(value = "最后获取信息的时间", name = "msgTimestamp")
    private Date msgTimestamp;
}
