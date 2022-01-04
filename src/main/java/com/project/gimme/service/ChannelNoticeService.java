package com.project.gimme.service;

import com.project.gimme.pojo.ChannelNotice;

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
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteChannelNotice(List<Integer> idList);
}
