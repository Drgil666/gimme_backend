package com.project.gimme.pojo.vo;

import com.project.gimme.pojo.PersonalMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author DrGilbert
 * @date 2022/1/14 16:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "个人信息中间类")
public class PersonalMsgVO extends PersonalMsg {
    /**
     * 操作群聊/频道名
     */
    @ApiModelProperty(value = "操作群聊/频道名", name = "objectNick")
    private String objectNick;
    /**
     * 被操作用户名
     */
    @ApiModelProperty(value = "被操作用户名", name = "operatorNick")
    private String operatorNick;
}