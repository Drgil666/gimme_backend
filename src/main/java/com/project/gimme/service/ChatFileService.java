package com.project.gimme.service;

import com.project.gimme.pojo.ChatFile;
import com.project.gimme.pojo.vo.ChatFileVO;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 17:03
 */
public interface ChatFileService {
    /**
     * 创建群文件
     *
     * @param chatFile 被创建的群文件
     * @return 是否成功
     */
    Boolean createChatFile(ChatFile chatFile);

    /**
     * 更新群文件
     *
     * @param chatFile 要更新的群文件
     * @return 影响行数
     */
    Long updateChatFile(ChatFile chatFile);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    ChatFile getChatFile(Integer id);

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param objectId 朋友/群聊/频道id
     * @param keyword  文件名
     * @param type     朋友/群聊/频道id类型
     * @return 查询的用户列表
     */
    List<ChatFile> getChatFileByGroupId(Integer type, Integer objectId, String keyword);

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param objectId 朋友/群聊/频道id
     * @param userId   用户id
     * @param keyword  文件名
     * @param type     朋友/群聊/频道id类型
     * @return 查询的用户列表
     */
    List<ChatFileVO> getGroupFileVoByObjectId(Integer type, Integer userId,
                                              Integer objectId, String keyword);
}
