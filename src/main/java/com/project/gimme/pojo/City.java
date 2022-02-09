package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/2/9 13:47
 */
@Data
@ApiModel(description = "城市实体类")
public class City {
    /**
     * id
     */
    @ApiModelProperty(value = "id", name = "id")
    private Integer id;
    /**
     * 城市名
     */
    @ApiModelProperty(value = "城市名", name = "nick")
    private String nick;
    /**
     * 省份id
     */
    @ApiModelProperty(value = "省份id", name = "provinceId")
    private Integer provinceId;
}
