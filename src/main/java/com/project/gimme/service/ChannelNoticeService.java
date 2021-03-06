package com.project.gimme.service;

import com.project.gimme.pojo.ChannelNotice;
import com.project.gimme.pojo.vo.ChatMsgVO;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:05
 */
public interface ChannelNoticeService {
    /**
     * 创建频道公告
     *
     * @param channelNotice 被创建的频道公告类
     * @return 是否成功
     */
    Boolean createChannelNotice(ChannelNotice channelNotice);

    /**
     * 更新频道
     *
     * @param channelNotice 要更新的频道公告
     * @return 影响行数
     */
    Long updateChannelNotice(ChannelNotice channelNotice);

    /**
     * 通过id获取频道公告
     *
     * @param id 频道公告id
     * @return 频道公告
     */
    ChannelNotice getChannelNotice(Integer id);

    /**
     * 批量删除频道
     *
     * @param idList    id列表
     * @param channelId 频道id
     * @return 影响行数
     */
    Long deleteChannelNotice(Integer channelId, List<Integer> idList);

    /**
     * 获取频道公告列表
     *
     * @param channelId 频道id
     * @return 频道公告列表
     */
    List<ChannelNotice> getChannelNoticeList(Integer channelId);

    /**
     * 获取频道公告记录
     *
     * @param userId          用户id
     * @param channelNoticeId 频道公告id
     * @return 记录列表
     */
    List<ChatMsgVO> getChannelNoticeInfo(Integer userId, Integer channelNoticeId);
}
