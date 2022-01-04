package com.project.gimme.service.impl;

import com.project.gimme.mapper.MsgBotMapper;
import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.MsgBot;
import com.project.gimme.service.MsgBotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:40
 */

@Service
public class MsgBotServiceImpl implements MsgBotService {
    @Resource
    private MsgBotMapper msgBotMapper;

    /**
     * 创建机器人
     *
     * @param msgBot 被创建的机器人
     * @return 是否成功
     */
    @Override
    public Boolean createMsgBot(MsgBot msgBot) {
        return msgBotMapper.createMsgBot(msgBot);
    }

    /**
     * 更新机器人
     *
     * @param msgBot 要更新的机器人
     * @return 影响行数
     */
    @Override
    public Long updateMsgBot(MsgBot msgBot) {
        return msgBotMapper.updateMsgBot(msgBot);
    }

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    @Override
    public Channel getMsgBot(Integer id) {
        return msgBotMapper.getMsgBot(id);
    }

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    @Override
    public Long deleteMsgBot(List<Integer> idList) {
        return msgBotMapper.deleteMsgBot(idList);
    }
}
