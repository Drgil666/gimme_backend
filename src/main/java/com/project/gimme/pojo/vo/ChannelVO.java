package com.project.gimme.pojo.vo;

import com.project.gimme.pojo.Channel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author DrGilbert
 * @date 2022/1/14 14:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "频道中间类")
public class ChannelVO extends Channel {
    /**
     * 总群人数
     */
    @ApiModelProperty(value = "总群人数", name = "totalCount")
    private Integer totalCount;
}
