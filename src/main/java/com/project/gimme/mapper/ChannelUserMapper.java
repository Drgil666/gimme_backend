package com.project.gimme.mapper;

import com.project.gimme.pojo.ChannelUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:23
 */
@Mapper
public interface ChannelUserMapper {
    /**
     * 创建频道成员
     *
     * @param channelUser 被创建的频道成员类
     * @return 是否成功
     */
    @Insert("insert into channel_user (channel_id, user_id,channel_nick,type,msg_timestamp) values " +
            "(#{channelUser.channelId},#{channelUser.userId}," +
            "#{channelUser.channelNick},#{channelUser.type},#{channelUser.msgTimestamp})")
    Boolean createChannelUser(@Param("channelUser") ChannelUser channelUser);

    /**
     * 更新频道成员
     *
     * @param channelUser 要更新的频道成员
     * @return 影响行数
     */
    @Update("update channel_user set channel_nick=#{channelUser.channelNick}," +
            "type=#{channelUser.type},msg_timestamp=#{channelUser.msgTimestamp} " +
            "where channel_id=#{channelUser.channelId} and " +
            "user_id=#{channelUser.userId}")
    Long updateChannelUser(@Param("channelUser") ChannelUser channelUser);

    /**
     * 通过id获取频道成员
     *
     * @param channelId 频道id
     * @param userId    用户id
     * @return 频道成员
     */
    @Select("select * from channel_user where " +
            "channel_id=#{channelId} and user_id=#{userId}")
    ChannelUser getChannelUser(@Param("channelId") Integer channelId,
                               @Param("userId") Integer userId);

    /**
     * 批量删除频道成员
     *
     * @param channelId 频道id
     * @param idList    id列表
     * @return 影响行数
     */
    Long deleteChannelUser(@Param("channelId") Integer channelId,
                           @Param("userId") List<Integer> idList);

    /**
     * 获取群成员个数
     *
     * @param channelId 频道id
     * @return 频道成员
     */
    @Select("select count(*) from channel_user where channel_id=#{channelId}")
    Integer getChannelMemberCount(@Param("channelId") Integer channelId);
}
