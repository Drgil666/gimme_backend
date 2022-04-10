package com.project.gimme.service;

import com.project.gimme.pojo.ChatMsg;
import com.project.gimme.pojo.vo.ChatMsgVO;
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
    List<ChatMsg> getChatMsgListByObjectId(String type, Integer objectId, String keyword);

    /**
     * 获取好友聊天/群聊/频道公告聊天记录中间类
     *
     * @param type     信息类型
     * @param objectId 对应id
     * @param keyword  关键词
     * @param userId   用户id
     * @return 聊天信息列表
     */
    List<ChatMsgVO> getChatMsgVoListByObjectId(Integer userId, String type, Integer objectId, String keyword);

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
     * @param userId  用户id
     * @param keyword 关键词
     * @return 好友消息
     */
    List<MessageVO> getMessageVoByUserId(Integer userId, String keyword);

    /**
     * 判断操作权限是否合法
     *
     * @param type     类型
     * @param userId   用户id
     * @param objectId 对象id
     * @return 是否合法
     */
    void checkValidity(String type, Integer userId, Integer objectId);

    /**
     * 统计频道公告的回复个数
     *
     * @param channelNoticeId 频道公告id
     * @return 回复个数
     */
    Integer getChannelNoticeCount(Integer channelNoticeId);

    /**
     * 获取聊天信息中间类
     *
     * @param userId    用户id
     * @param chatMsgId 聊天信息id
     * @return 聊天信息中间类
     */
    ChatMsgVO getChatMsgVO(Integer userId, Integer chatMsgId);

    /**
     * 获取好友信息
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @param keyword  关键词
     * @return 聊天信息
     */
    List<ChatMsgVO> getChatMsgVoListByFriend(Integer userId, Integer friendId, String keyword);
}
