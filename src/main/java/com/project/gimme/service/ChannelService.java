package com.project.gimme.service;

import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.vo.ChannelVO;
import com.project.gimme.pojo.vo.SearchVO;

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
    Long deleteChannelList(List<Integer> idList);

    /**
     * 根据关键词查找频道列表
     *
     * @param keyword 关键词
     * @param userId  用户id
     * @return 频道列表
     */
    List<Channel> getChannelList(Integer userId, String keyword);

    /**
     * 如果已加入频道，获取频道信息
     *
     * @param userId    用户id
     * @param channelId 频道id
     * @return 频道信息
     */
    ChannelVO getChannelVoIfJoin(Integer userId, Integer channelId);

    /**
     * 如果已加入频道，获取频道信息
     *
     * @param channelId 频道id
     * @return 频道信息
     */
    ChannelVO getChannelVoIfNotJoin(Integer channelId);

    /**
     * 删除频道
     *
     * @param id id
     * @return 影响行数
     */
    Long deleteChannel(Integer id);

    /**
     * 根据关键字查找
     *
     * @param userId     用户id
     * @param searchType 搜索类型
     * @param keyword    关键词
     * @return 查找结果列表
     */
    List<SearchVO> getChannelSearchVoList(Integer userId, String searchType, String keyword);
}
