package com.project.gimme.pojo.vo;

import com.project.gimme.pojo.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author DrGilbert
 * @date 2022/1/19 15:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "用户信息中间类")
public class UserVO extends User {
    /**
     * 好友备注
     */
    @ApiModelProperty(value = "好友备注", name = "note")
    private String note;
    /**
     * 群/频道昵称
     */
    @ApiModelProperty(value = "群/频道昵称", name = "otherNick")
    private String otherNick;
    /**
     * 国家名
     */
    @ApiModelProperty(value = "国家名", name = "countryNick")
    private String countryNick;
    /**
     * 省份名
     */
    @ApiModelProperty(value = "身份名", name = "provinceNick")
    private String provinceNick;
    /**
     * 城市名
     */
    @ApiModelProperty(value = "城市名", name = "cityNick")
    private String cityNick;
    /**
     * 职业名
     */
    @ApiModelProperty(value = "职业名", name = "occupationNick")
    private String occupationNick;
    /**
     * 群权限/频道权限
     */
    @ApiModelProperty(value = "群权限/频道权限", name = "otherType")
    private String otherType;
}
