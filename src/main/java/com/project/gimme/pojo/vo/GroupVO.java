package com.project.gimme.pojo.vo;

import com.project.gimme.pojo.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author DrGilbert
 * @date 2022/1/14 14:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "群聊中间类")
public class GroupVO extends Group {
    /**
     * 总群人数
     */
    @ApiModelProperty(value = "总群人数", name = "totalCount")
    private Integer totalCount;
    /**
     * 群昵称
     */
    @ApiModelProperty(value = "群昵称", name = "myNote")
    private String myNote;
    /**
     * 我的权限
     */
    @ApiModelProperty(value = "我的权限", name = "我的权限")
    private String myPriority;
}
