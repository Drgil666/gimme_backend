package com.project.gimme.mapper;

import com.project.gimme.pojo.ChatMsg;
import com.project.gimme.pojo.vo.MessageVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:58
 */
@Mapper
public interface ChatMsgMapper {
    /**
     * 创建聊天信息
     *
     * @param chatMsg 被创建的聊天信息类
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into chat_msg (text, owner_id, timestamp, object_id, type,msg_type)" +
            " values (#{chatMsg.text},#{chatMsg.ownerId},#{chatMsg.timeStamp}," +
            "#{chatMsg.objectId},#{chatMsg.type},#{chatMsg.msgType})")
    Boolean createChatMsg(@Param("chatMsg") ChatMsg chatMsg);

    /**
     * 更新聊天信息
     *
     * @param chatMsg 要更新的聊天信息
     * @return 影响行数
     */
    @Update("update chat_msg set text=#{chatMsg.text}," +
            "owner_id=#{chatMsg.ownerId},timestamp=#{chatMsg.timestamp}," +
            "object_id=#{chatMsg.objectId},type=#{chatMsg.type},msg_type=#{chatMsg.msgType}" +
            " where id=#{chatMsg.id}")
    Long updateChatMsg(@Param("chatMsg") ChatMsg chatMsg);

    /**
     * 通过id获取聊天信息
     *
     * @param id 聊天信息id
     * @return 聊天信息
     */
    @Select("select * from chat_msg where id=#{id}")
    ChatMsg getChatMsg(@Param("id") Integer id);

    /**
     * 获取好友聊天/群聊/频道公告聊天记录
     *
     * @param type     信息类型
     * @param objectId 对应id
     * @param keyword  关键词
     * @return 聊天信息列表
     */
    @Select("select * from chat_msg where type=#{type} and object_id=#{objectId} " +
            "and (text like CONCAT('%',#{keyword},'%') or owner_id like CONCAT('%',#{keyword},'%'))")
    List<ChatMsg> getChatMsgListByObjectId(@Param("type") String type,
                                           @Param("objectId") Integer objectId,
                                           @Param("keyword") String keyword);

    /**
     * 获取用户频道信息
     *
     * @param userId   用户id
     * @param objectId 好友/群聊/频道id
     * @return 聊天消息
     */
    @Select("select " +
            "channel_notice.text as text," +
            "channel.id as objectId," +
            "channel.nick as nick," +
            "channel.avatar as avatar," +
            "channel_notice.type as msgType," +
            "channel_notice.create_time as timestamp," +
            "(select count(channel_notice.id) from channel_notice,channel,channel_user " +
            "where channel_notice.channel_id=#{objectId} and channel.id=channel_notice.channel_id and channel.owner_id!=#{userId} " +
            "and channel_user.user_id=#{userId} and channel_user.channel_id=channel_notice.channel_id and " +
            "channel_notice.create_time >channel_user.msg_timestamp) as newMessageCount " +
            "from channel,channel_notice " +
            "where channel_notice.channel_id=channel.id and channel.id=#{objectId} " +
            "order by channel_notice.create_time DESC limit 1")
    MessageVO getChannelMessageVoByObjectId(@Param("userId") Integer userId,
                                            @Param("objectId") Integer objectId);

    /**
     * 获取用户好友信息
     *
     * @param userId   用户id
     * @param objectId 好友/群聊/频道id
     * @return 聊天消息
     */
    @Select("select chat_msg.text as text," +
            "chat_msg.timestamp as timestamp," +
            "chat_msg.type as type," +
            "chat_msg.msg_type as msgType," +
            "friend.friend_id as object_id," +
            "user.avatar as avatar," +
            "friend.friend_note as nick," +
            "(select count(*) from chat_msg,friend where owner_id=#{objectId} and object_id=#{userId} " +
            "and type='friend' and friend.user_id=object_id and friend.friend_id=owner_id " +
            "and chat_msg.timestamp > friend.msg_timestamp ) as newMessageCount " +
            "from chat_msg,user,friend " +
            "where chat_msg.type='friend' and (chat_msg.owner_id=#{userId} and chat_msg.object_id=#{objectId} " +
            "or chat_msg.owner_id=#{objectId} and chat_msg.object_id=#{userId}) " +
            "and friend.user_id=#{userId} and friend.friend_id=#{objectId} and user.id=#{objectId} " +
            "order by chat_msg.timestamp DESC limit 1")
    MessageVO getFriendMessageVoByObjectId(@Param("userId") Integer userId,
                                           @Param("objectId") Integer objectId);

    /**
     * 统计频道公告的回复个数
     *
     * @param channelNoticeId 频道公告id
     * @return 回复个数
     */
    @Select("select count(*) from chat_msg where type='channel' and object_id=#{channelNoticeId}")
    Integer getChannelNoticeCount(@Param("channelNoticeId") Integer channelNoticeId);

    /**
     * 获取用户频道信息
     *
     * @param userId   用户id
     * @param objectId 好友/群聊/频道id
     * @return 聊天消息
     */
    @Select("select chat_msg.text as text," +
            "chat_msg.timestamp as timestamp," +
            "chat_msg.type as type," +
            "chat_msg.msg_type as msgType," +
            "`group`.id as object_id," +
            "`group`.avatar as avatar," +
            "`group`.nick as nick," +
            "(select count(*) from chat_msg,group_user where chat_msg.owner_id!=#{userId} and chat_msg.object_id=#{objectId} " +
            "and chat_msg.type='group' and group_user.user_id=#{userId} and group_user.group_id=chat_msg.object_id and " +
            "chat_msg.timestamp > group_user.msg_timestamp) as newMessageCount " +
            "from chat_msg,`group` " +
            "where chat_msg.type='group' and " +
            "chat_msg.object_id=#{objectId} and `group`.id=chat_msg.object_id " +
            "order by chat_msg.timestamp DESC limit 1")
    MessageVO getGroupMessageVoByObjectId(@Param("userId") Integer userId,
                                          @Param("objectId") Integer objectId);

    /**
     * 批量删除聊天信息
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteChatMsg(@Param("id") List<Integer> idList);

    /**
     * 获取好友信息
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @param keyword  关键词
     * @return 聊天信息
     */
    @Select("select * from chat_msg where (owner_id=#{friendId} and object_id=#{userId} " +
            "or owner_id=#{userId} and object_id=#{friendId}) and text like CONCAT('%',#{keyword},'%')")
    List<ChatMsg> getChatMsgVoListByFriend(@Param("userId") Integer userId,
                                           @Param("friendId") Integer friendId,
                                           @Param("keyword") String keyword);
}
