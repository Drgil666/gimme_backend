package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:56
 */
@Data
@ApiModel(description = "群文件")
public class ChatFile {
    /**
     * 群文件id
     */
    @ApiModelProperty(value = "群文件id", name = "id", required = true)
    private Integer id;
    /**
     * 上传者id
     */
    @ApiModelProperty(value = "上传者id", name = "ownerId")
    private Integer ownerId;
    /**
     * 群聊id
     */
    @ApiModelProperty(value = "群聊id", name = "groupId")
    private Integer groupId;
    /**
     * 文件对应的mongoId
     */
    @ApiModelProperty(value = "文件对应的mongoId", name = "mongoId")
    private String mongoId;
    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名", name = "filename")
    private String filename;
}
