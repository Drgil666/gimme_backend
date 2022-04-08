package com.project.gimme.mapper;

import com.project.gimme.pojo.User;
import com.project.gimme.pojo.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/2 12:02
 */
@Mapper
public interface UserMapper {
    /**
     * 创建用户
     *
     * @param user 被创建的用户类
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into user (nick, avatar, city, occupation, company, " +
            "motto, birthday, mail, gender,password,personal_msg_timestamp) values " +
            "(#{user.nick},#{user.avatar},#{user.city}," +
            "#{user.occupation},#{user.company},#{user.motto}," +
            "#{user.birthday},#{user.mail},#{user.gender},#{user.password},#{user.personalMsgTimestamp})")
    Boolean createUser(@Param("user") User user);

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 影响行数
     */
    @Update("update user set nick=#{user.nick},avatar=#{user.avatar},city=#{user.city}," +
            "occupation=#{user.occupation},company=#{user.company},motto=#{user.motto}," +
            "birthday=#{user.birthday},mail=#{user.mail},gender=#{user.gender}," +
            "password=#{user.password},personal_msg_timestamp=#{user.personalMsgTimestamp}" +
            " where id=#{user.id}")
    Long updateUser(@Param("user") User user);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from user where id=#{id}")
    User getUser(@Param("id") Integer id);

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    @Select("select * from user where id like CONCAT('%',#{keyword},'%') or nick like CONCAT('%',#{keyword},'%')")
    List<User> getUserByIdAndNick(@Param("keyword") String keyword);

    /**
     * 如果已经是好友关系，根据好友关系查找用户信息
     *
     * @param friendId 好友id
     * @param userId   用户id
     * @return 对应的用户信息
     */
    @Select("select user.*,friend_note as note,true as isJoined " +
            "from user,friend where friend.friend_id=#{friendId} " +
            "and friend.user_id=#{userId} and user.id=friend.friend_id")
    UserVO getUserVoByFriendIfFriend(@Param("friendId") Integer friendId,
                                     @Param("userId") Integer userId);

    /**
     * 如果不是好友关系，查找用户信息
     *
     * @param userId 用户id
     * @return 对应的用户信息
     */
    @Select("select user.*,false as isJoined from user where user.id=#{userId}")
    UserVO getUserVoByFriendIfNotFriend(@Param("userId") Integer userId);

    /**
     * 若已是好友关系，根据群聊关系查找群成员信息
     *
     * @param memberId 成员id
     * @param groupId  群聊id
     * @param userId   用户id
     * @return 对应的用户信息
     */
    @Select("select user.*,group_user.group_nick as otherNick,friend.friend_note as note,true as isJoined " +
            "from user,group_user,friend " +
            "where user.id=#{memberId} and user.id=friend.friend_id and friend.user_id=#{userId} " +
            "and group_user.group_id=#{groupId} " +
            "and group_user.user_id=user.id")
    UserVO getUserVoByGroupIfFriend(@Param("groupId") Integer groupId,
                                    @Param("memberId") Integer memberId,
                                    @Param("userId") Integer userId);

    /**
     * 若不是好友关系，根据群聊关系查找群成员信息
     *
     * @param memberId 成员id
     * @param groupId  群聊id
     * @return 对应的用户信息
     */
    @Select("select user.*,group_user.group_nick as otherNick,false as isJoined " +
            "from user,group_user where user.id=#{memberId} " +
            "and group_user.group_id=#{groupId} and group_user.user_id=user.id")
    UserVO getUserVoByGroupIfNotFriend(@Param("groupId") Integer groupId,
                                       @Param("memberId") Integer memberId);

    /**
     * 若已是好友关系，根据频道关系查找群成员信息
     *
     * @param memberId  成员id
     * @param channelId 频道id
     * @param userId    用户id
     * @return 对应的用户信息
     */
    @Select("select user.*,channel_user.channel_nick as otherNick,friend.friend_note as note,true as isJoined  " +
            "from user,channel_user,friend " +
            "where user.id=#{memberId} and user.id=friend.friend_id and friend.user_id=#{userId} " +
            "and channel_user.channel_id=#{channelId} " +
            "and channel_user.user_id=user.id")
    UserVO getUserVoByChannelIfFriend(@Param("channelId") Integer channelId,
                                      @Param("memberId") Integer memberId,
                                      @Param("userId") Integer userId);

    /**
     * 若不是好友关系，根据频道关系查找群成员信息
     *
     * @param memberId  成员id
     * @param channelId 频道id
     * @return 对应的用户信息
     */
    @Select("select user.*,channel_user.channel_nick as otherNick,false as isJoined  " +
            "from user,channel_user where user.id=#{memberId} and " +
            "channel_user.channel_id=#{channelId} and channel_user.user_id=user.id")
    UserVO getUserVoByChannelIfNotFriend(@Param("channelId") Integer channelId,
                                         @Param("memberId") Integer memberId);

    /**
     * 通过userId和关键词获取好友列表
     *
     * @param userId  userId
     * @param keyword 关键词
     * @return 好友列表
     */
    @Select("select user.* from user,friend where friend.user_id=#{userId} "
            + "and friend.friend_id=user.id and (user.id like CONCAT('%',#{keyword},'%') " + "or user.nick like CONCAT('%',#{keyword},'%'))")
    List<User> getFriendUserList(@Param("userId") Integer userId, @Param("keyword") String keyword);

    /**
     * 通过userId和关键词获取好友列表
     *
     * @param userId  userId
     * @param keyword 关键词
     * @return 好友列表
     */
    @Select("select user.*,friend.friend_note as note,true as isJoined " +
            "from user,friend where friend.user_id=#{userId} " +
            "and friend.friend_id=user.id and " +
            "(user.id like CONCAT('%',#{keyword},'%') or user.nick like CONCAT('%',#{keyword},'%') or friend.friend_note like CONCAT('%',#{keyword},'%'))")
    List<UserVO> getFriendListInfo(@Param("userId") Integer userId, @Param("keyword") String keyword);

    /**
     * 获取群成员列表
     *
     * @param groupId 群id
     * @return 用户列表
     */
    @Select("select user.*,group_user.type as otherType,group_user.group_nick as otherNick" +
            " from user,group_user where group_id=#{groupId} and user_id=user.id")
    List<UserVO> getGroupMemberList(@Param("groupId") Integer groupId);

    /**
     * 获取群成员列表
     *
     * @param channelId 群id
     * @return 用户列表
     */
    @Select("select user.*,channel_user.type as otherType,channel_user.channel_nick as otherNick " +
            "from user,channel_user where channel_user.channel_id=#{channelId} and channel_user.user_id=user.id")
    List<UserVO> getChannelMemberList(@Param("channelId") Integer channelId);
}
