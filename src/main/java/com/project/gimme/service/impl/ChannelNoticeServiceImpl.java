package com.project.gimme.service.impl;

import com.project.gimme.mapper.ChannelMapper;
import com.project.gimme.mapper.ChannelNoticeMapper;
import com.project.gimme.mapper.UserMapper;
import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.ChannelNotice;
import com.project.gimme.pojo.vo.ChatMsgVO;
import com.project.gimme.service.ChannelNoticeService;
import com.project.gimme.utils.ChatMsgUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:06
 */
@Service
public class ChannelNoticeServiceImpl implements ChannelNoticeService {
    @Resource
    private ChannelNoticeMapper channelNoticeMapper;
    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 创建频道公告
     *
     * @param channelNotice 被创建的频道公告类
     * @return 是否成功
     */
    @Override
    public Boolean createChannelNotice(ChannelNotice channelNotice) {
        channelNotice.setCreateTime(new Date());
        return channelNoticeMapper.createChannelNotice(channelNotice);
    }

    /**
     * 更新频道
     *
     * @param channelNotice 要更新的频道公告
     * @return 影响行数
     */
    @Override
    public Long updateChannelNotice(ChannelNotice channelNotice) {
        return channelNoticeMapper.updateChannelNotice(channelNotice);
    }

    /**
     * 通过id获取频道公告
     *
     * @param channelId       频道id
     * @param channelNoticeId 频道公告id
     * @return 频道公告
     */
    @Override
    public ChannelNotice getChannelNotice(Integer channelId, Integer channelNoticeId) {
        if (channelNoticeId != null) {
            return channelNoticeMapper.getChannelNotice(channelNoticeId);
        } else {
            return channelNoticeMapper.getNewChannelNotice(channelId);
        }
    }

    /**
     * 批量删除频道
     *
     * @param idList    id列表
     * @param channelId 频道id
     * @return 影响行数
     */
    @Override
    public Long deleteChannelNotice(Integer channelId, List<Integer> idList) {
        return channelNoticeMapper.deleteChannelNotice(channelId, idList);
    }

    /**
     * 获取频道公告列表
     *
     * @param channelId 频道id
     * @return 频道公告列表
     */
    @Override
    public List<ChannelNotice> getChannelNoticeList(Integer channelId) {
        return channelNoticeMapper.getChannelNoticeList(channelId);
    }

    /**
     * 获取频道公告记录
     *
     * @param userId          用户id
     * @param channelNoticeId 频道公告id
     * @return 记录列表
     */
    @Override
    public List<ChatMsgVO> getChannelNoticeInfo(Integer userId, Integer channelNoticeId) {
        List<ChatMsgVO> chatMsgList = new ArrayList<>();
        ChatMsgVO chatMsgVO = new ChatMsgVO();
        ChannelNotice channelNotice = getChannelNotice(null, channelNoticeId);
        chatMsgVO.setTimeStamp(channelNotice.getCreateTime());
        chatMsgVO.setId(channelNoticeId);
        Channel channel = channelMapper.getChannel(channelNotice.getChannelId());
        chatMsgVO.setOwnerId(channel.getOwnerId());
        chatMsgVO.setObjectId(channelNoticeId);
        chatMsgVO.setType(ChatMsgUtil.Character.TYPE_CHANNEL_NOTICE.getName());
        chatMsgVO.setText(channelNotice.getText());
        chatMsgVO.setIsSelf(userId.equals(channel.getOwnerId()));
        chatMsgVO.setOwnerNick(userMapper.getUser(userId).getNick());
        chatMsgList.add(chatMsgVO);
        return chatMsgList;
    }
}
