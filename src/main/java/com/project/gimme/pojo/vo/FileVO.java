package com.project.gimme.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author Gilbert
 * @date 2022/2/24 16:33
 */
@Data
@ApiModel(description = "文件中间类")
public class FileVO {
    /**
     * 文件
     */
    @ApiModelProperty(value = "文件", name = "file")
    private MultipartFile file;
    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期", name = "createTime")
    private Date createTime;
    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型", name = "fileType")
    private String fileType;
    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名", name = "文件名")
    private String fileName;
    /**
     * 好友/群聊/频道id
     */
    @ApiModelProperty(value = "好友/群聊/频道id", name = "objectId")
    private Integer objectId;
    /**
     * 聊天类型
     */
    @ApiModelProperty(value = "聊天类型", name = "type")
    private String type;
}

