package com.project.gimme.mapper;

import com.project.gimme.pojo.ChatMsg;
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
    @Insert("insert into chat_msg (text, owner_id, timestamp, object_id, type)" +
            " values (#{chatMsg.text},#{chatMsg.ownerId},#{chatMsg.timestamp}," +
            "#{chatMsg.objectId},#{chatMsg.type})")
    Boolean createChatMsg(@Param("chatMsg") ChatMsg chatMsg);

    /**
     * 更新聊天信息
     *
     * @param chatMsg 要更新的聊天信息
     * @return 影响行数
     */
    @Update("update chat_msg set text=#{chatMsg.text}," +
            "owner_id=#{chatMsg.ownerId},timestamp=#{chatMsg.timestamp}," +
            "object_id=#{chatMsg.objectId},type=#{chatMsg.type}" +
            " where id=#{chatMsg.id}")
    Long updateChatMsg(@Param("chatMsg") ChatMsg chatMsg);

    /**
     * 通过id获取聊天信息
     *
     * @param id 聊天信息id
     * @return 聊天信息
     */
    @Select("select * from chat_msg where id=#{id}")
    ChatMsg getChannelUser(@Param("id") Integer id);

    /**
     * 获取好友聊天/群聊/频道公告聊天记录
     *
     * @param type     信息类型
     * @param objectId 对应id
     * @return 聊天信息列表
     */
    @Select("select * from chat_msg where type=#{type} and object_id=#{objectId}")
    List<ChatMsg> getChannelUserListByObjectId(@Param("type") Integer type,
                                               @Param("objectId") Integer objectId);

    /**
     * 批量删除聊天信息
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteChatMsg(@Param("id") List<Integer> idList);
}
