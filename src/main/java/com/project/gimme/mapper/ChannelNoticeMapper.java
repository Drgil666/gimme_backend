package com.project.gimme.mapper;

import com.project.gimme.pojo.ChannelNotice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:00
 */
@Mapper
public interface ChannelNoticeMapper {
    /**
     * 创建频道公告
     *
     * @param channelNotice 被创建的频道公告类
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into channel_notice (type, channel_id,text,create_time) values " +
            "(#{channelNotice.type},#{channelNotice.channelId}," +
            "#{channelNotice.text},#{channelNotice.createTime})")
    Boolean createChannelNotice(@Param("channelNotice") ChannelNotice channelNotice);

    /**
     * 更新频道
     *
     * @param channelNotice 要更新的频道公告
     * @return 影响行数
     */
    @Update("update channel_notice set type=#{channelNotice.type}," +
            "channel_id=#{channelNotice.channelId}," +
            "channel_id=#{channelNotice.createTime}," +
            "text=#{channelNotice.text} " +
            "where id=#{channelNotice.id}")
    Long updateChannelNotice(@Param("channelNotice") ChannelNotice channelNotice);

    /**
     * 通过id获取频道公告
     *
     * @param id 频道公告id
     * @return 频道公告
     */
    @Select("select * from channel_notice where id=#{id}")
    ChannelNotice getChannelNotice(@Param("id") Integer id);

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteChannelNotice(@Param("id") List<Integer> idList);
}
