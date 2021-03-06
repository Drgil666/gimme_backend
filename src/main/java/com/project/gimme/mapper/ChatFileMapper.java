package com.project.gimme.mapper;

import com.project.gimme.pojo.ChatFile;
import com.project.gimme.pojo.vo.ChatFileVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:58
 */
@Mapper
public interface ChatFileMapper {
    /**
     * 创建群文件
     *
     * @param chatFile 被创建的群文件
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into chat_file (owner_id, object_id, file_name, mongo_id,type,size,timestamp)" +
            " values (#{chatFile.ownerId},#{chatFile.objectId}," +
            "#{chatFile.filename},#{chatFile.mongoId},#{chatFile.type},#{chatFile.size},#{chatFile.timestamp})")
    Boolean createGroupFile(@Param("chatFile") ChatFile chatFile);

    /**
     * 更新群文件
     *
     * @param chatFile 要更新的群文件
     * @return 影响行数
     */
    @Update("update chat_file set owner_id=#{chatFile.ownerId}," +
            "object_id=#{chatFile.objectId},file_name=#{chatFile.filename}," +
            "mongo_id=#{chatFile.mongoId},type=#{chatFile.type},size=#{chatFile.size}," +
            "timestamp=#{chatFile.timestamp}" +
            " where id=#{chatFile.id}")
    Long updateGroupFile(@Param("chatFile") ChatFile chatFile);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from chat_file where id=#{id}")
    ChatFile getGroupFile(@Param("id") Integer id);

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param objectId 朋友/群聊/频道id
     * @param keyword  文件名
     * @param type     朋友/群聊/频道id类型
     * @return 查询的用户列表
     */
    @Select("select * from chat_file where type=#{type} and object_id=#{objectId} and file_name like CONCAT('%',#{keyword},'%')")
    List<ChatFile> getGroupFileByObjectId(@Param("type") Integer type,
                                          @Param("objectId") Integer objectId,
                                          @Param("keyword") String keyword);

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param objectId 朋友/群聊/频道id
     * @param userId   用户id
     * @param keyword  文件名
     * @param type     朋友/群聊/频道id类型
     * @return 查询的用户列表
     */
    @Select("select chat_file.*,group_user.group_nick as ownerNick from chat_file,group_user " +
            "where chat_file.type='group' and chat_file.object_id=#{objectId} and " +
            "group_user.user_id=chat_file.owner_id and group_user.group_id=#{objectId} " +
            "and file_name like CONCAT('%',#{keyword},'%')")
    List<ChatFileVO> getGroupFileVoByObjectId(@Param("userId") Integer userId,
                                              @Param("objectId") Integer objectId,
                                              @Param("keyword") String keyword);

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param objectId 朋友/群聊/频道id
     * @param userId   用户id
     * @param keyword  文件名
     * @param type     朋友/群聊/频道id类型
     * @return 查询的用户列表
     */
    @Select("select chat_file.*,channel_user.channel_nick as ownerNick from chat_file,channel_user " +
            "where chat_file.type='channel' and chat_file.object_id=#{objectId} and " +
            "channel_user.user_id=chat_file.owner_id and channel_user.channel_id=#{objectId} " +
            "and file_name like CONCAT('%',#{keyword},'%')")
    List<ChatFileVO> getChannelFileVoByObjectId(@Param("userId") Integer userId,
                                                @Param("objectId") Integer objectId,
                                                @Param("keyword") String keyword);

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param objectId 朋友/群聊/频道id
     * @param userId   用户id
     * @param keyword  文件名
     * @param type     朋友/群聊/频道id类型
     * @return 查询的用户列表
     */
    @Select("select chat_file.*,user.nick as ownerNick from chat_file,friend,user " +
            "where chat_file.type='friend' and " +
            "((chat_file.object_id=#{objectId} and friend.friend_id=chat_file.object_id and friend.user_id=#{userId}) " +
            "or ((chat_file.owner_id=#{objectId} and friend.user_id=chat_file.object_id and " +
            "friend.friend_id=#{userId}))) " +
            "and friend.friend_id=chat_file.object_id and user.id=friend.friend_id " +
            "and file_name like CONCAT('%',#{keyword},'%')")
    List<ChatFileVO> getFriendFileVoByObjectId(@Param("userId") Integer userId,
                                               @Param("objectId") Integer objectId,
                                               @Param("keyword") String keyword);

    /**
     * 批量删除聊天文件
     *
     * @param idList 文件的id列表
     * @return 影响行数
     */
    Long deleteChatFile(@Param("id") List<Integer> idList);

}
