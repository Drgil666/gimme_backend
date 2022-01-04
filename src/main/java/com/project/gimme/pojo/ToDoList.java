package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:14
 */
@Data
@ApiModel(description = "待办")
public class ToDoList {
    /**
     * 待办id
     */
    @ApiModelProperty(value = "待办id", name = "id", required = true)
    private Integer id;
    /**
     * 发起人id
     */
    @ApiModelProperty(value = "发起人id", name = "ownerId")
    private Integer ownerId;
    /**
     * 待办内容
     */
    @ApiModelProperty(value = "待办内容", name = "text")
    private String text;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始内容", name = "startTime")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束内容", name = "endTime")
    private Date endTime;
}
