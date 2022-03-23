package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Gilbert
 * @date 2022/3/23 12:30
 */
@Data
@ApiModel(description = "搜索结果中间类")
public class SearchVO {
    /**
     * 会话头像
     */
    @ApiModelProperty(value = "会话头像", name = "avatar")
    private String avatar;
    /**
     * 会话名
     */
    private String objectNick;
    /**
     * 会话id
     */
    private Integer objectId;
    /**
     * 会话类型
     */
    private String objectType;
}
