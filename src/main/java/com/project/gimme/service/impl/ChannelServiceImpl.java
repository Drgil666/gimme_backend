package com.project.gimme.service.impl;

import com.project.gimme.mapper.ChannelMapper;
import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.vo.ChannelVO;
import com.project.gimme.service.ChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 10:42
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    @Resource
    private ChannelMapper channelMapper;

    /**
     * 创建频道
     *
     * @param channel 被创建的频道类
     * @return 是否成功
     */
    @Override
    public Boolean createChannel(Channel channel) {
        return channelMapper.createChannel(channel);
        //ToDoList:要创建频道成员进去
    }

    /**
     * 更新频道
     *
     * @param channel 要更新的频道
     * @return 影响行数
     */
    @Override
    public Long updateChannel(Channel channel) {
        return channelMapper.updateChannel(channel);
    }

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    @Override
    public Channel getChannel(Integer id) {
        return channelMapper.getChannel(id);
    }

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    @Override
    public Long deleteChannel(List<Integer> idList) {
        return channelMapper.deleteChannel(idList);
    }

    /**
     * 根据关键词查找频道列表
     *
     * @param keyword 关键词
     * @param userId  用户id
     * @return 频道列表
     */
    @Override
    public List<Channel> getChannelList(String keyword, Integer userId) {
        return channelMapper.getChannelList(keyword, userId);
    }

    /**
     * 如果已加入频道，获取频道信息
     *
     * @param userId    用户id
     * @param channelId 频道id
     * @return 频道信息
     */
    @Override
    public ChannelVO getChannelVoIfJoin(Integer userId, Integer channelId) {
        return channelMapper.getChannelVoIfJoin(userId, channelId);
    }

    /**
     * 如果已加入频道，获取频道信息
     *
     * @param channelId 频道id
     * @return 频道信息
     */
    @Override
    public ChannelVO getChannelVoIfNotJoin(Integer channelId) {
        return channelMapper.getChannelVoIfNotJoin(channelId);
    }
}
