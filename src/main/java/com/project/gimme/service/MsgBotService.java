package com.project.gimme.service;

import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.MsgBot;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:39
 */
public interface MsgBotService {
    /**
     * 创建机器人
     *
     * @param msgBot 被创建的机器人
     * @return 是否成功
     */
    Boolean createMsgBot(MsgBot msgBot);

    /**
     * 更新机器人
     *
     * @param msgBot 要更新的机器人
     * @return 影响行数
     */
    Long updateMsgBot(MsgBot msgBot);

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    Channel getMsgBot(Integer id);

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteMsgBot(List<Integer> idList);
}
