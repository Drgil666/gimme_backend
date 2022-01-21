package com.project.gimme.mapper;

import com.project.gimme.pojo.Channel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 10:32
 */
@Mapper
public interface ChannelMapper {
    /**
     * 创建频道
     *
     * @param channel 被创建的频道类
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into channel (owner_id, nick, create_time,description) values " +
            "(#{channel.ownerId},#{channel.nick},#{channel.createTime},#{channel.description})")
    Boolean createChannel(@Param("channel") Channel channel);

    /**
     * 更新频道
     *
     * @param channel 要更新的频道
     * @return 影响行数
     */
    @Update("update channel set owner_id=#{channel.ownerId}," +
            "nick=#{channel.nick},create_time=#{channel.createTime}," +
            "description=#{channel.description} " +
            "where id=#{channel.id}")
    Long updateChannel(@Param("channel") Channel channel);

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    @Select("select * from channel where id=#{id}")
    Channel getChannel(@Param("id") Integer id);

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteChannel(@Param("id") List<Integer> idList);
}
