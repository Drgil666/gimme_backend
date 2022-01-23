package com.project.gimme.service.impl;

import com.project.gimme.mapper.ChatFileMapper;
import com.project.gimme.pojo.ChatFile;
import com.project.gimme.service.ChatFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 17:04
 */
@Service
public class ChatFileServiceImpl implements ChatFileService {
    @Resource
    private ChatFileMapper chatFileMapper;

    /**
     * 创建群文件
     *
     * @param chatFile 被创建的群文件
     * @return 是否成功
     */
    @Override
    public Boolean createChatFile(ChatFile chatFile) {
        return chatFileMapper.createGroupFile(chatFile);
    }

    /**
     * 更新群文件
     *
     * @param chatFile 要更新的群文件
     * @return 影响行数
     */
    @Override
    public Long updateChatFile(ChatFile chatFile) {
        return chatFileMapper.updateGroupFile(chatFile);
    }

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public ChatFile getChatFile(Integer id) {
        return chatFileMapper.getGroupFile(id);
    }

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param groupId 群聊id
     * @param keyword 文件名
     * @return 查询的用户列表
     */
    @Override
    public List<ChatFile> getChatFileByGroupId(Integer groupId, String keyword) {
        return chatFileMapper.getGroupByGroupId(groupId, keyword);
    }
}
