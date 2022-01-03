package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Gilbert
 * @date 2020/8/11 18:51
 * 输出用vo
 */
@Data
@ApiModel(description = "ReturnPage")
public class ReturnPage<T> {
    /**
     * 分页、排序完的数据
     */
    @ApiModelProperty(value = "分页、排序完的数据", name = "data", required = true)
    private List<T> data;
    /**
     * 数据总数（未分页的）
     */
    @ApiModelProperty(value = "数据总数（未分页的）", name = "total", required = true)
    private Long total;
    /**
     * 每页容量
     */
    @ApiModelProperty(value = "每页容量", name = "pageSize", required = true)
    private Integer pageSize;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", name = "current", required = true)
    private Integer current;
}
