package com.project.gimme.service.impl;

import com.project.gimme.mapper.ChannelNoticeMapper;
import com.project.gimme.pojo.ChannelNotice;
import com.project.gimme.service.ChannelNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:06
 */
@Service
public class ChannelNoticeServiceImpl implements ChannelNoticeService {
    @Resource
    private ChannelNoticeMapper channelNoticeMapper;

    /**
     * 创建频道公告
     *
     * @param channelNotice 被创建的频道公告类
     * @return 是否成功
     */
    @Override
    public Boolean createChannelNotice(ChannelNotice channelNotice) {
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
     * @param id 频道公告id
     * @return 频道公告
     */
    @Override
    public ChannelNotice getChannelNotice(Integer id) {
        return channelNoticeMapper.getChannelNotice(id);
    }

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    @Override
    public Long deleteChannelNotice(List<Integer> idList) {
        return channelNoticeMapper.deleteChannelNotice(idList);
    }
}
