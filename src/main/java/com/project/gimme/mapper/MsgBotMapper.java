package com.project.gimme.mapper;

import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.MsgBot;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:35
 */
@Mapper
public interface MsgBotMapper {
    /**
     * 创建机器人
     *
     * @param msgBot 被创建的机器人
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into msg_bot (text,group_id, cron) values " +
            "(#{msgBot.text},#{msgBot.groupId},#{msgBot.cron})")
    Boolean createMsgBot(@Param("msgBot") MsgBot msgBot);

    /**
     * 更新机器人
     *
     * @param msgBot 要更新的机器人
     * @return 影响行数
     */
    @Update("update msg_bot set text=#{msgBot.text}," +
            "group_id=#{msgBot.groupId},cron=#{msgBot.cron} " +
            "where id=#{msgBot.id}")
    Long updateMsgBot(@Param("msgBot") MsgBot msgBot);

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    @Select("select * from msg_bot where id=#{id}")
    Channel getMsgBot(@Param("id") Integer id);

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteMsgBot(@Param("id") List<Integer> idList);
}
