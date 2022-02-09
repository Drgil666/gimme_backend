package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/2/9 13:47
 */
@Data
@ApiModel(description = "省份实体类")
public class Province {
    /**
     * id
     */
    @ApiModelProperty(value = "id", name = "id")
    private Integer id;
    /**
     * 省份名
     */
    @ApiModelProperty(value = "省份名", name = "nick")
    private String nick;
    /**
     * 国家id
     */
    @ApiModelProperty(value = "国家id", name = "countryId")
    private Integer countryId;
}
