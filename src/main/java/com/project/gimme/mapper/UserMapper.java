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
    @Insert("insert into user (nick, avatar, city, country, occupation, company, " +
            "motto, birthday, mail, province,gender,password) values " +
            "(#{user.nick},#{user.avatar},#{user.city},#{user.country}," +
            "#{user.occupation},#{user.company},#{user.motto}," +
            "#{user.birthday},#{user.mail},#{user.province},#{user.gender},#{user.password})")
    Boolean createUser(@Param("user") User user);

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 影响行数
     */
    @Update("update user set nick=#{user.nick},avatar=#{user.avatar},city=#{user.city}," +
            "country=#{user.country},occupation=#{user.occupation},company=#{user.company}," +
            "motto=#{user.motto},birthday=#{user.birthday},mail=#{user.mail}," +
            "province=#{user.province},gender=#{user.gender},password=#{user.password}" +
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
    @Select("select user.*,friend_note as note,country.nick as countryNick," +
            "province.nick as provinceNick,city.nick as cityNick," +
            "occupation.nick as occupationNick from user,friend,country,province,city,occupation" +
            " where friend.friend_id=#{friendId} and friend.user_id=#{userId} and user.id=friend.friend_id " +
            "and user.province=province.id and user.country=country.id and user.city=city.id" +
            " and user.occupation=occupation.id")
    UserVO getUserVoByFriendIfFriend(@Param("friendId") Integer friendId,
                                     @Param("userId") Integer userId);

    /**
     * 如果不是好友关系，查找用户信息
     *
     * @param userId 用户id
     * @return 对应的用户信息
     */
    @Select("select user.*,country.nick as countryNick," +
            "province.nick as provinceNick,city.nick as cityNick," +
            "occupation.nick as occupationNick from user,country,province,city,occupation" +
            " where user.id=#{userId} " +
            "and user.province=province.id and user.country=country.id and user.city=city.id" +
            " and user.occupation=occupation.id")
    UserVO getUserVoByFriendIfNotFriend(@Param("userId") Integer userId);

    /**
     * 若已是好友关系，根据群聊关系查找群成员信息
     *
     * @param memberId 成员id
     * @param groupId  群聊id
     * @param userId   用户id
     * @return 对应的用户信息
     */
    @Select("select user.*,group_user.group_nick as otherNick,friend.friend_note as note," +
            "country.nick as countryNick,province.nick as provinceNick,city.nick as cityNick," +
            "occupation.nick as occupationNick from user,group_user,friend,country,province,city,occupation " +
            "where user.id=#{memberId} and user.id=friend.friend_id and friend.user_id=#{userId} " +
            "and user.province=province.id and user.country=country.id and user.city=city.id " +
            "and user.occupation=occupation.id and group_user.group_id=#{groupId} " +
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
    @Select("select user.*,group_user.group_nick as otherNick," +
            "country.nick as countryNick,province.nick as provinceNick,city.nick as cityNick," +
            "occupation.nick as occupationNick from " +
            "user,group_user,country,province,city,occupation " +
            "where user.id=#{memberId} and user.province=province.id and user.country=country.id" +
            " and user.city=city.id and user.occupation=occupation.id and " +
            "group_user.group_id=#{groupId} and group_user.user_id=user.id")
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
    @Select("select user.*,channel_user.channel_nick as otherNick,friend.friend_note as note," +
            "country.nick as countryNick,province.nick as provinceNick,city.nick as cityNick," +
            "occupation.nick as occupationNick from user,channel_user,friend,country,province,city,occupation " +
            "where user.id=#{memberId} and user.id=friend.friend_id and friend.user_id=#{userId} " +
            "and user.province=province.id and user.country=country.id and user.city=city.id " +
            "and user.occupation=occupation.id and channel_user.channel_id=#{channelId} " +
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
    @Select("select user.*,channel_user.channel_nick as otherNick," +
            "country.nick as countryNick,province.nick as provinceNick,city.nick as cityNick," +
            "occupation.nick as occupationNick from " +
            "user,channel_user,country,province,city,occupation " +
            "where user.id=#{memberId} and user.province=province.id and user.country=country.id " +
            "and user.city=city.id and user.occupation=occupation.id and " +
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
    @Select("select user.* from user,friend where friend.user_id=#{userId} " + "and friend.friend_id=user.id and (user.id like CONCAT('%',#{keyword},'%') " + "or user.nick like CONCAT('%',#{keyword},'%'))")
    List<User> getFriendUserList(@Param("userId") Integer userId, @Param("keyword") String keyword);

    /**
     * 通过userId和关键词获取好友列表
     *
     * @param userId  userId
     * @param keyword 关键词
     * @return 好友列表
     */
    @Select("select user.*,country.nick as countryNick,friend.friend_note as note," + "province.nick as provinceNick,city.nick as cityNick," + "occupation.nick as occupationNick from occupation,country,province,city,user,friend where " + "friend.user_id=#{userId} and province.id=user.province and country.id=user.country and city.id=user.city " + "and occupation.id=user.occupation " + "and friend.friend_id=user.id and (user.id like CONCAT('%',#{keyword},'%') " + "or user.nick like CONCAT('%',#{keyword},'%'))")
    List<UserVO> getFriendListInfo(@Param("userId") Integer userId, @Param("keyword") String keyword);

    /**
     * 获取群成员列表
     *
     * @param groupId 群id
     * @return 用户列表
     */
    @Select("select user.* from user,group_user where group_id=#{groupId} and user_id=user.id")
    List<UserVO> getGroupMemberList(@Param("groupId") Integer groupId);
    //TODO:需要修改

    /**
     * 获取群成员列表
     *
     * @param channelId 群id
     * @return 用户列表
     */
    @Select("select user.* from user,channel_user where channel_id=#{channelId} and user_id=user.id")
    List<UserVO> getChannelMemberList(@Param("channelId") Integer channelId);
    //TODO:需要修改
}
