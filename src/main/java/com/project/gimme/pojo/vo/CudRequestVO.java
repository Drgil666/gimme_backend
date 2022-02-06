package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gilbert
 * @version 1.0
 * @date 2020/7/19 12:50
 */
@Data
@NoArgsConstructor
@ApiModel(description = "CudRequestVO")
public class CudRequestVO<T, K> {
    public static final String CREATE_METHOD = "create";
    public static final String UPDATE_METHOD = "update";
    public static final String DELETE_METHOD = "delete";
    @ApiModelProperty(value = "操作类型", name = "method", required = true, example = "CREATE_METHOD")
    private String method;
    @ApiModelProperty(value = "实体类", name = "data", required = true)
    private T data;
    @ApiModelProperty(value = "删除用的id列表", name = "key", required = true)
    private List<K> key;

    public void setMethod(String method) {
        if (method != null) {
            method = method.toLowerCase();
        }
        this.method = method;
    }
}
