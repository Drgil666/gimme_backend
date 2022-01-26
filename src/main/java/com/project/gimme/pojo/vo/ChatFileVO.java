package com.project.gimme.pojo.vo;

import com.project.gimme.pojo.ChatFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author DrGilbert
 * @date 2022/1/26 13:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "聊天文件中间类")
public class ChatFileVO extends ChatFile {
    /**
     * 上传者昵称
     */
    @ApiModelProperty(value = "上传者昵称", name = "ownerNick")
    private String ownerNick;
}
