package com.project.gimme.service.impl;

import com.project.gimme.mapper.ChannelMapper;
import com.project.gimme.mapper.ChatMsgMapper;
import com.project.gimme.mapper.FriendMapper;
import com.project.gimme.mapper.GroupMapper;
import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.ChatMsg;
import com.project.gimme.pojo.Friend;
import com.project.gimme.pojo.Group;
import com.project.gimme.pojo.vo.MessageVO;
import com.project.gimme.service.ChatMsgService;
import com.project.gimme.service.RedisService;
import com.project.gimme.utils.ChatMsgUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:07
 */
@Service
public class ChatMsgServiceImpl implements ChatMsgService {
    @Resource
    private ChatMsgMapper chatMsgMapper;
    @Resource
    private FriendMapper friendMapper;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private RedisService redisService;

    /**
     * 创建聊天信息
     *
     * @param chatMsg 被创建的聊天信息类
     * @return 是否成功
     */
    @Override
    public Boolean createChatMsg(ChatMsg chatMsg) {
        return chatMsgMapper.createChatMsg(chatMsg);
    }

    /**
     * 更新聊天信息
     *
     * @param chatMsg 要更新的聊天信息
     * @return 影响行数
     */
    @Override
    public Long updateChatMsg(ChatMsg chatMsg) {
        return chatMsgMapper.updateChatMsg(chatMsg);
    }

    /**
     * 通过id获取聊天信息
     *
     * @param id 聊天信息id
     * @return 聊天信息
     */
    @Override
    public ChatMsg getChatMsg(Integer id) {
        return chatMsgMapper.getChatMsg(id);
    }

    /**
     * 获取好友聊天/群聊/频道公告聊天记录
     *
     * @param type     信息类型
     * @param objectId 对应id
     * @param keyword  关键词
     * @return 聊天信息列表
     */
    @Override
    public List<ChatMsg> getChatMsgListByObjectId(Integer type, Integer objectId, String keyword) {
        return chatMsgMapper.getChatMsgListByObjectId(type, objectId, keyword);
    }

    /**
     * 批量删除聊天信息
     *
     * @param idList id列表
     * @return 影响行数
     */
    @Override
    public Long deleteChatMsg(List<Integer> idList) {
        return chatMsgMapper.deleteChatMsg(idList);
    }

    /**
     * 获取用户好友/群聊/频道信息
     *
     * @param userId 用户id
     * @return 好友消息
     */
    @Override
    public List<MessageVO> getMessageVoByUserId(Integer userId) {
        List<Friend> friendList = friendMapper.getFriendList(userId);
        List<Group> groupList = groupMapper.getGroupList(userId, "");
        List<Channel> channelList = channelMapper.getChannelList("", userId);
        List<MessageVO> messageVOList = new ArrayList<>();
        for (ChatMsgUtil.Character character : ChatMsgUtil.CHARACTER_LIST) {
            if (character.equals(ChatMsgUtil.Character.TYPE_FRIEND)) {
                for (Friend friend : friendList) {
                    MessageVO messageVO = chatMsgMapper.getMessageVoByObjectId(userId, character.getCode(), friend.getFriendId());
                    messageVOList.add(messageVO);
                }
            } else if (character.equals(ChatMsgUtil.Character.TYPE_GROUP)) {
                for (Group group : groupList) {
                    MessageVO messageVO = chatMsgMapper.getMessageVoByObjectId(userId, character.getCode(), group.getId());
                    messageVOList.add(messageVO);
                }
            } else if (character.equals(ChatMsgUtil.Character.TYPE_CHANNEL)) {
                for (Channel channel : channelList) {
                    MessageVO messageVO = chatMsgMapper.getMessageVoByObjectId(userId, character.getCode(), channel.getId());
                    messageVOList.add(messageVO);
                }
            }
        }
        messageVOList.sort((a, b) -> {
            if (a.getTimestamp().equals(b.getTimestamp())) {
                return 0;
            } else {
                return a.getTimestamp().after(b.getTimestamp()) ? 1 : -1;
            }
        });
        return messageVOList;
    }

    /**
     * 判断是否合法
     *
     * @param type     类型
     * @param userId   用户id
     * @param objectId 对象id
     * @return 是否合法
     */
    @Override
    public Boolean checkValidity(Integer type, Integer userId, Integer objectId) {
        if (type.equals(ChatMsgUtil.Character.TYPE_FRIEND.getCode())) {
            return redisService.checkFriendToken(userId, type);
        } else if (type.equals(ChatMsgUtil.Character.TYPE_GROUP.getCode())) {
            return redisService.getGroupAuthorityToken(userId, objectId) != null;
        } else if (type.equals(ChatMsgUtil.Character.TYPE_CHANNEL.getCode())) {
            return redisService.getChannelAuthorityToken(userId, objectId) != null;
        } else {
            return false;
        }
    }
}
