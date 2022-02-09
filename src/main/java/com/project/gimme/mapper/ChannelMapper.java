package com.project.gimme.mapper;

import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.vo.ChannelVO;
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
    @Insert("insert into channel (owner_id, nick, create_time,description,avatar) values " +
            "(#{channel.ownerId},#{channel.nick},#{channel.createTime},#{channel.description},#{channel.avatar})")
    Boolean createChannel(@Param("channel") Channel channel);

    /**
     * 更新频道
     *
     * @param channel 要更新的频道
     * @return 影响行数
     */
    @Update("update channel set owner_id=#{channel.ownerId}," +
            "nick=#{channel.nick},create_time=#{channel.createTime}," +
            "description=#{channel.description},avatar=#{channel.avatar} " +
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
     * 根据关键词查找频道列表
     *
     * @param keyword 关键词
     * @param userId  用户id
     * @return 频道列表
     */
    @Select("select channel.* from channel,channel_user where " +
            "channel.nick like CONCAT('%',#{keyword},'%') " +
            "and channel_user.channel_id=channel.id and channel_user.user_id=#{userId}")
    List<Channel> getChannelList(@Param("keyword") String keyword, @Param("userId") Integer userId);

    /**
     * 如果已加入频道，获取频道信息
     *
     * @param userId    用户id
     * @param channelId 频道id
     * @return 频道信息
     */
    @Select("select channel.*,(select count(*) from channel_user where channel_id=#{channelId})" +
            "as totalCount,(select channel_user.channel_nick from channel_user where " +
            "channel_user.user_id=#{userId} and channel_id=#{channelId}) as myNote from channel where channel.id=#{channelId}")
    ChannelVO getChannelVoIfJoin(@Param("userId") Integer userId, @Param("channelId") Integer channelId);

    /**
     * 如果未加入频道，获取频道信息
     *
     * @param channelId 频道id
     * @return 频道信息
     */
    @Select("select channel.*,(select count(*) from channel_user where channel_id=#{channelId})" +
            "as totalCount from channel where channel.id=#{channelId}")
    ChannelVO getChannelVoIfNotJoin(@Param("channelId") Integer channelId);

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteChannelList(@Param("id") List<Integer> idList);

    /**
     * 删除频道
     *
     * @param id id
     * @return 影响行数
     */
    @Delete(" delete from channel where id=#{id}")
    Long deleteChannel(@Param("id") Integer id);
}
