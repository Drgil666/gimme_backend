package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/2/3 16:11
 */
@Data
@ApiModel(description = "信息通知用户实体类")
public class PersonalMsgUser {
    /**
     * 信息通知id
     */
    @ApiModelProperty(value = "信息通知id", name = "personalMsgId")
    private Integer personalMsgId;
    /**
     * 接受者id
     */
    @ApiModelProperty(value = "接受者id", name = "acceptId")
    private Integer acceptId;
}
