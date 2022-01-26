package com.project.gimme.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:56
 */
@Data
@ApiModel(description = "聊天文件实体类")
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
     * 所在的好友/群聊/频道id
     */
    @ApiModelProperty(value = "群聊id", name = "groupId")
    private Integer objectId;
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
    /**
     * 好友/群聊/频道id
     */
    @ApiModelProperty(value = "好友/群聊/频道id", name = "type")
    private Integer type;
    /**
     * 文件大小(bit为单位)
     */
    @ApiModelProperty(value = "文件大小(bit为单位)", name = "size")
    private Long size;
    /**
     * 文件上传日期
     */
    @ApiModelProperty(value = "文件上传日期", name = "timestamp")
    private Date timestamp;
}
