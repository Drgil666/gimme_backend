package com.project.gimme.service;

import com.project.gimme.pojo.ChatMsg;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:06
 */
public interface ChatMsgService {
    /**
     * 创建聊天信息
     *
     * @param chatMsg 被创建的聊天信息类
     * @return 是否成功
     */
    Boolean createChatMsg(ChatMsg chatMsg);

    /**
     * 更新聊天信息
     *
     * @param chatMsg 要更新的聊天信息
     * @return 影响行数
     */
    Long updateChatMsg(ChatMsg chatMsg);

    /**
     * 通过id获取聊天信息
     *
     * @param id 聊天信息id
     * @return 聊天信息
     */
    ChatMsg getChannelUser(Integer id);

    /**
     * 获取好友聊天/群聊/频道公告聊天记录
     *
     * @param type     信息类型
     * @param objectId 对应id
     * @return 聊天信息列表
     */
    List<ChatMsg> getChannelUserListByObjectId(Integer type,
                                               Integer objectId);

    /**
     * 批量删除聊天信息
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteChatMsg(List<Integer> idList);
}
