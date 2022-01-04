package com.project.gimme.service.impl;

import com.project.gimme.mapper.ChannelMapper;
import com.project.gimme.pojo.Channel;
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
}
