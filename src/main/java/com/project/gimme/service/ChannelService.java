package com.project.gimme.service;

import com.project.gimme.pojo.Channel;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 10:41
 */
public interface ChannelService {
    /**
     * 创建频道
     *
     * @param channel 被创建的频道类
     * @return 是否成功
     */
    Boolean createChannel(Channel channel);

    /**
     * 更新频道
     *
     * @param channel 要更新的频道
     * @return 影响行数
     */
    Long updateChannel(Channel channel);

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    Channel getChannel(Integer id);

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteChannel(List<Integer> idList);
}
