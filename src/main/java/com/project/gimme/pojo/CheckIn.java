package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:09
 */
@Data
@ApiModel(description = "签到")
public class CheckIn {
    /**
     * 签到id
     */
    @ApiModelProperty(value = "签到id", name = "id", required = true)
    private Integer id;
    /**
     * 群聊id
     */
    @ApiModelProperty(value = "群聊id", name = "groupId")
    private Integer groupId;
    /**
     * 签到MAC地址/GPS地址
     */
    @ApiModelProperty(value = "签到MAC地址/GPS地址", name = "address")
    private String address;
    /**
     * 签到类型
     */
    @ApiModelProperty(value = "签到类型", name = "type")
    private Integer type;
}
