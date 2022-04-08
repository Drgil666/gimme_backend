package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/2 11:51
 */
@Data
@ApiModel(description = "用户信息实体类")
public class User {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "id", required = true)
    private Integer id;
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", name = "nick", required = true)
    private String nick;
    /**
     * 用户头像的mongoId
     */
    @ApiModelProperty(value = "用户头像的mongoId", name = "avatar")
    private String avatar;
    /**
     * 城市
     */
    @ApiModelProperty(value = "城市", name = "city")
    private String city;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", name = "gender")
    private Integer gender;
    /**
     * 用户生日
     */
    @ApiModelProperty(value = "用户生日", name = "birthday")
    private Date birthday;
    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱", name = "mail")
    private String mail;
    /**
     * 用户职业
     */
    @ApiModelProperty(value = "用户职业", name = "occupation")
    private Integer occupation;
    /**
     * 用户公司
     */
    @ApiModelProperty(value = "用户公司", name = "company")
    private String company;
    /**
     * 用户个性签名
     */
    @ApiModelProperty(value = "用户个性签名", name = "motto")
    private String motto;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password")
    private String password;
    /**
     * 个人信息时间戳
     */
    @ApiModelProperty(value = "个人信息时间戳", name = "personalMsgTimestamp")
    private Date personalMsgTimestamp;
}
