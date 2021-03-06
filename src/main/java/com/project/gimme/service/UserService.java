package com.project.gimme.service;

import com.project.gimme.pojo.User;
import com.project.gimme.pojo.vo.UserVO;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/2 13:07
 */
public interface UserService {
    /**
     * 创建用户
     *
     * @param user 要创建的用户
     * @return 是否创建成功
     */
    Boolean createUser(User user);

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 更新好的User
     */
    Long updateUser(User user);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    User getUser(Integer id);

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    List<User> getUserByIdAndNick(String keyword);

    /**
     * 根据好友关系查找用户信息
     *
     * @param friendId 好友id
     * @param userId   用户id
     * @return 对应的用户信息
     */
    UserVO getUserVoByFriendIfFriend(Integer friendId, Integer userId);

    /**
     * 如果不是好友关系，查找用户信息
     *
     * @param userId 用户id
     * @return 对应的用户信息
     */
    UserVO getUserVoByFriendIfNotFriend(Integer userId);

    /**
     * 根据群聊关系查找群成员信息
     *
     * @param memberId 成员id
     * @param groupId  群聊id
     * @param userId   用户id
     * @return 对应的用户信息
     */
    UserVO getUserVoByGroupIfFriend(Integer groupId, Integer memberId, Integer userId);

    /**
     * 若不是好友关系，根据群聊关系查找群成员信息
     *
     * @param memberId 成员id
     * @param groupId  群聊id
     * @return 对应的用户信息
     */
    UserVO getUserVoByGroupIfNotFriend(Integer groupId, Integer memberId);

    /**
     * 若已是好友关系，根据频道关系查找群成员信息
     *
     * @param memberId  成员id
     * @param channelId 频道id
     * @param userId    用户id
     * @return 对应的用户信息
     */
    UserVO getUserVoByChannelIfFriend(Integer channelId, Integer memberId, Integer userId);

    /**
     * 若不是好友关系，根据频道关系查找群成员信息
     *
     * @param memberId  成员id
     * @param channelId 频道id
     * @return 对应的用户信息
     */
    UserVO getUserVoByChannelIfNotFriend(Integer channelId, Integer memberId);

    /**
     * 登录
     *
     * @param userId   用户id
     * @param password 密码
     * @return 登录成功的token
     */
    String login(Integer userId, String password);

    /**
     * 通过userId和关键词获取好友列表
     *
     * @param userId  userId
     * @param keyword 关键词
     * @return 好友列表
     */
    List<User> getFriendUserList(Integer userId, String keyword);

    /**
     * 通过userId和关键词获取好友列表
     *
     * @param userId  userId
     * @param keyword 关键词
     * @return 好友列表
     */
    List<UserVO> getFriendListInfo(Integer userId, String keyword);

    /**
     * 获取群成员列表
     *
     * @param userId  用户id
     * @param groupId 群id
     * @return 用户列表
     */
    List<UserVO> getGroupMemberList(Integer userId, Integer groupId);

    /**
     * 获取频道成员列表
     *
     * @param userId    用户id
     * @param channelId 群id
     * @return 用户列表
     */
    List<UserVO> getChannelMemberList(Integer userId, Integer channelId);
}
