package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:39
 */
@Data
@ApiModel(description = "待办用户")
public class ToDoUser {
    /**
     * 待办id
     */
    @ApiModelProperty(value = "待办id", name = "toDoId")
    private Integer toDoId;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "userId")
    private Integer userId;
    /**
     * 待办状态
     */
    @ApiModelProperty(value = "待办状态", name = "status")
    private Integer status;
}
