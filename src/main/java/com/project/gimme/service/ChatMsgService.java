package com.project.gimme.service;

import com.project.gimme.pojo.ChatMsg;
import com.project.gimme.pojo.vo.MessageVO;

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
    ChatMsg getChatMsg(Integer id);

    /**
     * 获取好友聊天/群聊/频道公告聊天记录
     *
     * @param type     信息类型
     * @param objectId 对应id
     * @param keyword  关键词
     * @return 聊天信息列表
     */
    List<ChatMsg> getChatMsgListByObjectId(Integer type, Integer objectId, String keyword);

    /**
     * 批量删除聊天信息
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteChatMsg(List<Integer> idList);

    /**
     * 获取用户好友/群聊/频道信息
     *
     * @param userId 用户id
     * @return 好友消息
     */
    List<MessageVO> getMessageVoByUserId(Integer userId);

    /**
     * 判断是否合法
     *
     * @param type     类型
     * @param userId   用户id
     * @param objectId 对象id
     * @return 是否合法
     */
    void checkValidity(Integer type, Integer userId, Integer objectId);
}
