package com.project.gimme.service.impl;

import com.project.gimme.exception.ErrorCode;
import com.project.gimme.mapper.*;
import com.project.gimme.pojo.*;
import com.project.gimme.pojo.vo.ChatMsgVO;
import com.project.gimme.pojo.vo.MessageVO;
import com.project.gimme.service.ChatMsgService;
import com.project.gimme.service.RedisService;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.ChatMsgUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private UserMapper userMapper;
    @Resource
    private ChannelNoticeMapper channelNoticeMapper;

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
    public List<ChatMsg> getChatMsgListByObjectId(String type, Integer objectId, String keyword) {
        return chatMsgMapper.getChatMsgListByObjectId(type, objectId, keyword);
    }

    /**
     * 获取好友聊天/群聊/频道公告聊天记录中间类
     *
     * @param userId   用户id
     * @param type     信息类型
     * @param objectId 对应id
     * @param keyword  关键词
     * @return 聊天信息列表
     */
    @Override
    public List<ChatMsgVO> getChatMsgVoListByObjectId(Integer userId, String type, Integer objectId, String keyword) {
        if (ChatMsgUtil.Character.TYPE_CHANNEL.getName().equals(type)) {
            List<ChannelNotice> channelNotices = channelNoticeMapper.getChannelNoticeList(objectId);
            return channelNotices.stream()
                    .parallel()
                    .map(channelNotice -> {
                        ChatMsgVO chatMsgVO = new ChatMsgVO();
                        Channel channel = channelMapper.getChannel(objectId);
                        chatMsgVO.setOwnerId(channel.getOwnerId());
                        chatMsgVO.setObjectId(channelNotice.getId());
                        chatMsgVO.setId(channelNotice.getId());
                        chatMsgVO.setText(channelNotice.getText());
                        chatMsgVO.setTimeStamp(channelNotice.getCreateTime());
                        chatMsgVO.setIsSelf(userId.equals(channel.getOwnerId()));
                        chatMsgVO.setType(ChatMsgUtil.Character.TYPE_CHANNEL_NOTICE.getName());
                        chatMsgVO.setChannelNoticeCount(getChannelNoticeCount(channelNotice.getId()));
                        User user = userMapper.getUser(channel.getOwnerId());
                        chatMsgVO.setOwnerNick(user.getNick());
                        //TODO:获取昵称的方式需要再修改
                        return chatMsgVO;
                    }).collect(Collectors.toList());
        } else {
            List<ChatMsg> chatMsgList = chatMsgMapper.getChatMsgListByObjectId(type, objectId, keyword);
            return chatMsgList.stream()
                    .parallel()
                    .map(chatMsg -> {
                        ChatMsgVO chatMsgVO = new ChatMsgVO();
                        chatMsgVO.setOwnerId(chatMsg.getOwnerId());
                        chatMsgVO.setId(chatMsg.getId());
                        chatMsgVO.setTimeStamp(chatMsg.getTimeStamp());
                        chatMsgVO.setType(chatMsg.getType());
                        chatMsgVO.setText(chatMsg.getText());
                        chatMsgVO.setObjectId(chatMsg.getObjectId());
                        chatMsgVO.setIsSelf(chatMsg.getOwnerId().equals(userId));
                        User user = userMapper.getUser(chatMsg.getOwnerId());
                        chatMsgVO.setOwnerNick(user.getNick());
                        //TODO:获取昵称的方式需要再修改
                        return chatMsgVO;
                    }).collect(Collectors.toList());
        }
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
                    MessageVO messageVO = chatMsgMapper.getFriendMessageVoByObjectId(
                            userId, friend.getFriendId());
                    if (messageVO != null) {
                        messageVOList.add(messageVO);
                    }
                }
            } else if (character.equals(ChatMsgUtil.Character.TYPE_GROUP)) {
                for (Group group : groupList) {
                    MessageVO messageVO = chatMsgMapper.getGroupMessageVoByObjectId(
                            userId, group.getId());
                    if (messageVO != null) {
                        messageVOList.add(messageVO);
                    }
                }
            } else if (character.equals(ChatMsgUtil.Character.TYPE_CHANNEL)) {
                for (Channel channel : channelList) {
                    MessageVO messageVO = chatMsgMapper.getChannelMessageVoByObjectId(
                            userId, channel.getId());
                    if (messageVO != null) {
                        messageVO.setType(character.getName());
                        messageVOList.add(messageVO);
                    }
                }
            }
        }
        messageVOList.sort((a, b) -> {
            if (a.getTimestamp().equals(b.getTimestamp())) {
                return 0;
            } else {
                return a.getTimestamp().before(b.getTimestamp()) ? 1 : -1;
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
     */
    @Override
    public void checkValidity(String type, Integer userId, Integer objectId) {
        boolean flag = false;
        if (type.equals(ChatMsgUtil.Character.TYPE_FRIEND.getName())) {
            if (redisService.checkFriendToken(userId, objectId)) {
                flag = true;
            }
        } else if (type.equals(ChatMsgUtil.Character.TYPE_GROUP.getName())) {
            if (redisService.getGroupAuthorityToken(userId, objectId) != null) {
                flag = true;
            }
        } else if (type.equals(ChatMsgUtil.Character.TYPE_CHANNEL.getName())) {
            if (redisService.getChannelAuthorityToken(userId, objectId) != null) {
                flag = true;
            }
        }
        AssertionUtil.notNull(flag, ErrorCode.BIZ_PARAM_ILLEGAL, "操作失败!");
    }

    /**
     * 统计频道公告的回复个数
     *
     * @param channelNoticeId 频道公告id
     * @return 回复个数
     */
    @Override
    public Integer getChannelNoticeCount(Integer channelNoticeId) {
        return chatMsgMapper.getChannelNoticeCount(channelNoticeId);
    }

    /**
     * 获取聊天信息中间类
     *
     * @param userId    用户id
     * @param chatMsgId 聊天信息id
     * @return 聊天信息中间类
     */
    @Override
    public ChatMsgVO getChatMsgVO(Integer userId, Integer chatMsgId) {
        ChatMsgVO chatMsgVO = new ChatMsgVO();
        ChatMsg chatMsg = chatMsgMapper.getChatMsg(chatMsgId);
        chatMsgVO.setText(chatMsg.getText());
        chatMsgVO.setId(chatMsg.getId());
        chatMsgVO.setType(chatMsg.getType());
        chatMsgVO.setTimeStamp(chatMsg.getTimeStamp());
        chatMsgVO.setObjectId(chatMsg.getObjectId());
        chatMsgVO.setOwnerId(chatMsg.getOwnerId());
        chatMsgVO.setIsSelf(chatMsgVO.getOwnerId().equals(userId));
        User user = userMapper.getUser(chatMsgVO.getOwnerId());
        chatMsgVO.setOwnerNick(user.getNick());
        return chatMsgVO;
    }
}
