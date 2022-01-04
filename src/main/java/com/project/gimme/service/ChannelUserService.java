package com.project.gimme.service;

import com.project.gimme.pojo.ChannelUser;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:36
 */
public interface ChannelUserService {
    /**
     * 创建频道成员
     *
     * @param channelUser 被创建的频道成员类
     * @return 是否成功
     */
    Boolean createChannelUser(ChannelUser channelUser);

    /**
     * 更新频道成员
     *
     * @param channelUser 要更新的频道成员
     * @return 影响行数
     */
    Long updateChannelUser(ChannelUser channelUser);

    /**
     * 通过id获取频道成员
     *
     * @param channelId 频道id
     * @param userId    用户id
     * @return 频道成员
     */
    ChannelUser getChannelUser(Integer channelId, Integer userId);

    /**
     * 批量删除频道成员
     *
     * @param channelId 频道id
     * @param idList    id列表
     * @return 影响行数
     */
    Long deleteChannelUser(Integer channelId, List<Integer> idList);
}
