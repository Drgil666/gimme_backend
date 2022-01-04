package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:02
 */
@Data
@ApiModel(description = "签到用户")
public class CheckInUser {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId", required = true)
    private Integer userId;
    /**
     * 签到id
     */
    @ApiModelProperty(value = "签到id", name = "checkInId", required = true)
    private Integer checkInId;
    /**
     * 签到时间
     */
    @ApiModelProperty(value = "签到时间", name = "timestamp")
    private Date timestamp;
}
