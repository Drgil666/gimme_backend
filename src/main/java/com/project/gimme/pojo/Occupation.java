package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/2/9 13:47
 */
@Data
@ApiModel(description = "职业实体类")
public class Occupation {
    /**
     * id
     */
    @ApiModelProperty(value = "id", name = "id")
    private Integer id;
    /**
     * 国家名
     */
    @ApiModelProperty(value = "国家名", name = "nick")
    private String nick;
}
