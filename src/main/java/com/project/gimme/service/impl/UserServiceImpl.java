package com.project.gimme.service.impl;

import com.project.gimme.exception.ErrorCode;
import com.project.gimme.mapper.UserMapper;
import com.project.gimme.pojo.User;
import com.project.gimme.pojo.vo.UserVO;
import com.project.gimme.service.RedisService;
import com.project.gimme.service.UserService;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.BcryptUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/2 13:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisService redisService;

    /**
     * 创建用户
     *
     * @param user 要创建的用户
     * @return 是否创建成功
     */
    @Override
    public Boolean createUser(User user) {
        //密码加密
        user.setPassword(BcryptUtil.encode(user.getPassword()));
        return userMapper.createUser(user);
    }

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 更新好的User
     */
    @Override
    public Long updateUser(User user) {
        //密码加密
        user.setPassword(BcryptUtil.encode(user.getPassword()));
        return userMapper.updateUser(user);
    }

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    @Override
    public List<User> getUserByIdAndNick(String keyword) {
        return userMapper.getUserByIdAndNick(keyword);
    }

    /**
     * 根据好友关系查找用户信息
     *
     * @param friendId 好友id
     * @param userId   用户id
     * @return 对应的用户信息
     */
    @Override
    public UserVO getUserVoByFriendIfFriend(Integer friendId, Integer userId) {
        return userMapper.getUserVoByFriendIfFriend(friendId, userId);
    }

    /**
     * 如果不是好友关系，查找用户信息
     *
     * @param userId 用户id
     * @return 对应的用户信息
     */
    @Override
    public UserVO getUserVoByFriendIfNotFriend(Integer userId) {
        return userMapper.getUserVoByFriendIfNotFriend(userId);
    }

    /**
     * 根据群聊关系查找群成员信息
     *
     * @param groupId  群聊id
     * @param memberId 成员id
     * @param userId   用户id
     * @return 对应的用户信息
     */
    @Override
    public UserVO getUserVoByGroupIfFriend(Integer groupId, Integer memberId, Integer userId) {
        return userMapper.getUserVoByGroupIfFriend(groupId, memberId, userId);
    }

    /**
     * 若已是好友关系，根据频道关系查找群成员信息
     *
     * @param channelId 频道id
     * @param memberId  成员id
     * @param userId    用户id
     * @return 对应的用户信息
     */
    @Override
    public UserVO getUserVoByChannelIfFriend(Integer channelId, Integer memberId, Integer userId) {
        return userMapper.getUserVoByChannelIfFriend(channelId, memberId, userId);
    }

    /**
     * 若不是好友关系，根据频道关系查找群成员信息
     *
     * @param channelId 频道id
     * @param memberId  成员id
     * @return 对应的用户信息
     */
    @Override
    public UserVO getUserVoByChannelIfNotFriend(Integer channelId, Integer memberId) {
        return userMapper.getUserVoByChannelIfNotFriend(channelId, memberId);
    }

    /**
     * 登录
     *
     * @param userId   用户id
     * @param password 密码
     * @return 登录成功的token
     */
    @Override
    public String login(Integer userId, String password) {
        AssertionUtil.notNull(userId, ErrorCode.BIZ_PARAM_ILLEGAL, "userId不可为空!");
        User user = userMapper.getUser(userId);
        if (BcryptUtil.checkPassword(password, user.getPassword())) {
            return redisService.createUserLoginToken(userId);
        } else {
            return null;
        }
    }
}
