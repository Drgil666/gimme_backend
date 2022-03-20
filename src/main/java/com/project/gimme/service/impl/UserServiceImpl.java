package com.project.gimme.service.impl;

import com.project.gimme.exception.ErrorCode;
import com.project.gimme.mapper.UserMapper;
import com.project.gimme.pojo.User;
import com.project.gimme.pojo.vo.UserVO;
import com.project.gimme.service.RedisService;
import com.project.gimme.service.UserService;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.BcryptUtil;
import com.project.gimme.utils.UserUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
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
     * 手动激活mysql
     */
    @PostConstruct
    public void init() {
        userMapper.getUser(1);
    }

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
        AssertionUtil.notNull(friendId, ErrorCode.BIZ_PARAM_ILLEGAL, "friendId不可为空!");
        AssertionUtil.notNull(userId, ErrorCode.BIZ_PARAM_ILLEGAL, "userId不可为空!");
        UserVO userVO = userMapper.getUserVoByFriendIfFriend(friendId, userId);
        AssertionUtil.notNull(userVO, ErrorCode.INNER_PARAM_ILLEGAL, "该频道或该成员不存在!");
        userVO.setPassword(null);
        return userVO;
    }

    /**
     * 如果不是好友关系，查找用户信息
     *
     * @param userId 用户id
     * @return 对应的用户信息
     */
    @Override
    public UserVO getUserVoByFriendIfNotFriend(Integer userId) {
        AssertionUtil.notNull(userId, ErrorCode.BIZ_PARAM_ILLEGAL, "userId不可为空!");
        UserVO userVO = userMapper.getUserVoByFriendIfNotFriend(userId);
        AssertionUtil.notNull(userVO, ErrorCode.INNER_PARAM_ILLEGAL, "该频道或该成员不存在!");
        userVO.setPassword(null);
        return userVO;
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
        AssertionUtil.notNull(memberId, ErrorCode.BIZ_PARAM_ILLEGAL, "memberId不可为空!");
        AssertionUtil.notNull(userId, ErrorCode.BIZ_PARAM_ILLEGAL, "userId不可为空!");
        AssertionUtil.notNull(groupId, ErrorCode.BIZ_PARAM_ILLEGAL, "groupId不可为空!");
        UserVO userVO = userMapper.getUserVoByGroupIfFriend(groupId, memberId, userId);
        AssertionUtil.notNull(userVO, ErrorCode.INNER_PARAM_ILLEGAL, "该频道或该成员不存在!");
        userVO.setPassword(null);
        return userVO;
    }

    /**
     * 若不是好友关系，根据群聊关系查找群成员信息
     *
     * @param groupId  群聊id
     * @param memberId 成员id
     * @return 对应的用户信息
     */
    @Override
    public UserVO getUserVoByGroupIfNotFriend(Integer groupId, Integer memberId) {
        AssertionUtil.notNull(memberId, ErrorCode.BIZ_PARAM_ILLEGAL, "memberId不可为空!");
        AssertionUtil.notNull(groupId, ErrorCode.BIZ_PARAM_ILLEGAL, "groupId不可为空!");
        UserVO userVO = userMapper.getUserVoByGroupIfNotFriend(groupId, memberId);
        AssertionUtil.notNull(userVO, ErrorCode.INNER_PARAM_ILLEGAL, "该频道或该成员不存在!");
        userVO.setPassword(null);
        return userVO;
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
        AssertionUtil.notNull(memberId, ErrorCode.BIZ_PARAM_ILLEGAL, "memberId不可为空!");
        AssertionUtil.notNull(channelId, ErrorCode.BIZ_PARAM_ILLEGAL, "channelId不可为空!");
        AssertionUtil.notNull(userId, ErrorCode.BIZ_PARAM_ILLEGAL, "userId不可为空!");
        UserVO userVO = userMapper.getUserVoByChannelIfFriend(channelId, memberId, userId);
        AssertionUtil.notNull(userVO, ErrorCode.INNER_PARAM_ILLEGAL, "该频道或该成员不存在!");
        userVO.setPassword(null);
        return userVO;
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
        AssertionUtil.notNull(memberId, ErrorCode.BIZ_PARAM_ILLEGAL, "memberId不可为空!");
        AssertionUtil.notNull(channelId, ErrorCode.BIZ_PARAM_ILLEGAL, "channelId不可为空!");
        UserVO userVO = userMapper.getUserVoByChannelIfNotFriend(channelId, memberId);
        AssertionUtil.notNull(userVO, ErrorCode.INNER_PARAM_ILLEGAL, "该频道或该成员不存在!");
        userVO.setPassword(null);
        return userVO;
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
        AssertionUtil.notNull(user, ErrorCode.BIZ_PARAM_ILLEGAL, "用户名或密码错误!");
        if (BcryptUtil.checkPassword(password, user.getPassword())) {
            return redisService.createUserLoginToken(userId);
        } else {
            return null;
        }
    }

    /**
     * 通过userId和关键词获取好友列表
     *
     * @param userId  userId
     * @param keyword 关键词
     * @return 好友列表
     */
    @Override
    public List<User> getFriendUserList(Integer userId, String keyword) {
        List<User> userList = userMapper.getFriendUserList(userId, keyword);
        for (User user : userList) {
            user.setPassword(null);
        }
        return userList;
    }

    /**
     * 通过userId和关键词获取好友列表
     *
     * @param userId  userId
     * @param keyword 关键词
     * @return 好友列表
     */
    @Override
    public List<UserVO> getFriendListInfo(Integer userId, String keyword) {
        List<UserVO> userList = userMapper.getFriendListInfo(userId, keyword);
        for (UserVO userVO : userList) {
            userVO.setPassword(null);
        }
        return userList;
    }

    /**
     * 获取群成员列表
     *
     * @param groupId 群id
     * @return 用户列表
     */
    @Override
    public List<UserVO> getGroupMemberList(Integer groupId) {
        List<UserVO> userList = userMapper.getGroupMemberList(groupId);
        for (User user : userList) {
            user.setPassword(null);
        }
        userList.sort((a, b) -> {
            Integer codeA = UserUtil.getGroupCharacterByName(a.getOtherType());
            Integer codeB = UserUtil.getGroupCharacterByName(b.getOtherType());
            String nameA = (StringUtils.isEmpty(a.getOtherNick())) ? a.getNick() : a.getOtherNick();
            String nameB = (StringUtils.isEmpty(b.getOtherNick())) ? b.getNick() : b.getOtherNick();
            if (!codeA.equals(codeB)) {
                return (codeA >= codeB) ? 1 : -1;
            } else if (!nameA.equals(nameB)) {
                return (nameA.compareTo(nameB) > 0) ? 1 : -1;
            } else {
                return 0;
            }
        });
        return userList;
    }

    /**
     * 获取频道成员列表
     *
     * @param channelId 群id
     * @param limit     个数限制
     * @return 用户列表
     */
    @Override
    public List<UserVO> getChannelMemberList(Integer channelId, Integer limit) {
        List<UserVO> userList = userMapper.getChannelMemberList(channelId);
        for (User user : userList) {
            user.setPassword(null);
        }
        userList.sort((a, b) -> {
            Integer codeA = UserUtil.getChannelCharacterByName(a.getOtherType());
            Integer codeB = UserUtil.getChannelCharacterByName(b.getOtherType());
            String nameA = (StringUtils.isEmpty(a.getOtherNick())) ? a.getNick() : a.getOtherNick();
            String nameB = (StringUtils.isEmpty(b.getOtherNick())) ? b.getNick() : b.getOtherNick();
            if (!codeA.equals(codeB)) {
                return (codeA >= codeB) ? 1 : -1;
            } else if (!nameA.equals(nameB)) {
                return (nameA.compareTo(nameB) > 0) ? 1 : -1;
            } else {
                return 0;
            }
        });
        return userList;
    }
}
