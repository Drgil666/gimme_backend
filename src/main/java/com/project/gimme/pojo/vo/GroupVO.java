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
}
