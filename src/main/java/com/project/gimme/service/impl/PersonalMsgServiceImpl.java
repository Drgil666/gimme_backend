package com.project.gimme.service.impl;

import com.project.gimme.exception.ErrorCode;
import com.project.gimme.mapper.PersonalMsgMapper;
import com.project.gimme.pojo.*;
import com.project.gimme.pojo.vo.PersonalMsgVO;
import com.project.gimme.service.*;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.ChatMsgUtil;
import com.project.gimme.utils.PersonalMsgUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.project.gimme.utils.UserUtil.*;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:54
 */
@Service
public class PersonalMsgServiceImpl implements PersonalMsgService {
    @Resource
    private PersonalMsgMapper personalMsgMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private GroupUserService groupUserService;
    @Resource
    private ChannelUserService channelUserService;
    @Resource
    private UserService userService;
    @Resource
    private GroupService groupService;
    @Resource
    private ChannelService channelService;

    /**
     * 创建信息通知
     *
     * @param personalMsg 被创建的信息通知
     * @return 是否成功
     */
    @Override
    public Boolean createPersonalMsg(PersonalMsg personalMsg) {
        return personalMsgMapper.createPersonalMsg(personalMsg);
    }

    /**
     * 创建添加好友个人信息
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @param note     备注
     * @return
     */
    @Override
    public PersonalMsg createInsertFriendPersonalMsg(Integer userId, Integer friendId, String note) {
        //添加好友，被添加方有消息
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(userId);
        personalMsg.setOwnerId(friendId);
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_FRIEND.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.FriendPersonalMsg.TYPE_INSERT_FRIEND.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_CHOOSE.getCode());
        personalMsg.setNote(note);
        personalMsg.setObjectId(userId);
        return personalMsg;
    }

    /**
     * 创建添加好友个人信息
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @return
     */
    @Override
    public PersonalMsg createDeleteFriendPersonalMsg(Integer userId, Integer friendId) {
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(userId);
        personalMsg.setOwnerId(friendId);
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_FRIEND.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.FriendPersonalMsg.TYPE_DELETE_FRIEND.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_NULL.getCode());
        personalMsg.setNote(null);
        personalMsg.setObjectId(null);
        return personalMsg;
    }

    /**
     * 创建添加群聊个人信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 个人信息
     */
    @Override
    public PersonalMsg createInsertGroupPersonalMsg(Integer userId, Integer groupId) {
        //创建群聊，群主有消息
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(null);
        personalMsg.setOwnerId(userId);
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_GROUP.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.GroupPersonalMsg.TYPE_INSERT_GROUP.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_NULL.getCode());
        personalMsg.setNote(null);
        personalMsg.setObjectId(groupId);
        return personalMsg;
    }

    /**
     * 创建删除群聊个人信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 个人信息
     */
    @Override
    public PersonalMsg createDeleteGroupPersonalMsg(Integer userId, Integer groupId) {
        //删除群聊，所有群成员有消息
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(null);
        personalMsg.setOwnerId(userId);
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_GROUP.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.GroupPersonalMsg.TYPE_DELETE_GROUP.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_NULL.getCode());
        personalMsg.setNote(null);
        personalMsg.setObjectId(groupId);
        return personalMsg;
    }

    /**
     * 创建新增群聊个人信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @param note    备注
     * @return 个人信息
     */
    @Override
    public PersonalMsg createInsertGroupMemberPersonalMsg(Integer userId, Integer groupId, String note) {
        //新增群聊成员，群主会受到消息
        GroupUser groupUser = groupUserService.getGroupUserByType(groupId, GroupCharacter.TYPE_GROUP_OWNER.getName()).get(0);
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(userId);
        personalMsg.setOwnerId(groupUser.getUserId());
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_GROUP.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.GroupPersonalMsg.TYPE_INSERT_GROUP_MEMBER.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_CHOOSE.getCode());
        personalMsg.setNote(note);
        personalMsg.setObjectId(groupId);
        return personalMsg;
    }

    /**
     * 创建删除群聊成员个人信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 个人信息
     */
    @Override
    public PersonalMsg createDeleteGroupMemberPersonalMsg(Integer userId, Integer groupId) {
        //删除群成员，被删除的有消息
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(null);
        personalMsg.setOwnerId(userId);
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_GROUP.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.GroupPersonalMsg.TYPE_DELETE_GROUP_MEMBER.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_NULL.getCode());
        personalMsg.setNote(null);
        personalMsg.setObjectId(groupId);
        return personalMsg;
    }

    /**
     * 创建添加频道个人信息
     *
     * @param userId    用户id
     * @param channelId 频道
     * @return 个人信息
     */
    @Override
    public PersonalMsg createInsertChannelPersonalMsg(Integer userId, Integer channelId) {
        //创建频道，频道主有消息
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(null);
        personalMsg.setOwnerId(userId);
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_CHANNEL.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.ChannelPersonalMsg.TYPE_INSERT_CHANNEL.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_NULL.getCode());
        personalMsg.setNote(null);
        personalMsg.setObjectId(channelId);
        return personalMsg;
    }

    /**
     * 创建删除频道个人信息
     *
     * @param userId    用户id
     * @param channelId 频道id
     * @return 个人信息
     */
    @Override
    public PersonalMsg createDeleteChannelPersonalMsg(Integer userId, Integer channelId) {
        //删除群聊，所有群成员有消息
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(null);
        personalMsg.setOwnerId(userId);
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_CHANNEL.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.ChannelPersonalMsg.TYPE_DELETE_CHANNEL.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_NULL.getCode());
        personalMsg.setNote(null);
        personalMsg.setObjectId(channelId);
        return personalMsg;
    }

    /**
     * 创建添加群聊成员个人信息
     *
     * @param userId    用户id
     * @param channelId 频道id
     * @param note      备注
     * @return 个人信息
     */
    @Override
    public PersonalMsg createInsertChannelMemberPersonalMsg(Integer userId, Integer channelId, String note) {
        //新增群聊成员，群主会受到消息
        Channel channel = channelService.getChannel(channelId);
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(userId);
        personalMsg.setOwnerId(channel.getOwnerId());
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_CHANNEL.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.ChannelPersonalMsg.TYPE_INSERT_CHANNEL_MEMBER.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_CHOOSE.getCode());
        personalMsg.setNote(note);
        personalMsg.setObjectId(channelId);
        return personalMsg;
    }

    /**
     * 创建删除频道成员个人信息
     *
     * @param userId    用户id
     * @param channelId 群聊id
     * @return 个人信息
     */
    @Override
    public PersonalMsg createDeleteChannelMemberPersonalMsg(Integer userId, Integer channelId) {
        //删除群成员，被删除的有消息
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOperatorId(null);
        personalMsg.setOwnerId(userId);
        personalMsg.setObjectType(ChatMsgUtil.Character.TYPE_CHANNEL.getName());
        personalMsg.setTimestamp(new Date());
        personalMsg.setType(PersonalMsgUtil.ChannelPersonalMsg.TYPE_DELETE_CHANNEL_MEMBER.getName());
        personalMsg.setStatus(PersonalMsgUtil.Status.TYPE_NULL.getCode());
        personalMsg.setNote(null);
        personalMsg.setObjectId(channelId);
        return personalMsg;
    }

    /**
     * 更新信息通知
     *
     * @param personalMsg 要更新的信息通知
     * @return 影响行数
     */
    @Override
    public Long updatePersonalMsg(PersonalMsg personalMsg) {
        return personalMsgMapper.updatePersonalMsg(personalMsg);
    }

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    @Override
    public PersonalMsg getPersonalMsg(Integer id) {
        return personalMsgMapper.getPersonalMsg(id);
    }

    /**
     * 通过id获取个人信息通知
     *
     * @param id 频道id
     * @return 频道
     */
    @Override
    public PersonalMsgVO getPersonalMsgVO(Integer id) {
        return getPersonalMsgVO(personalMsgMapper.getPersonalMsg(id));
    }

    /**
     * 批量删除通知
     *
     * @param id 删除的id
     * @return 影响行数
     */
    @Override
    public Long deletePersonalMsg(List<Integer> id) {
        return personalMsgMapper.deletePersonalMsg(id);
    }

    /**
     * 通过用户id获取个人信息通知
     *
     * @param userId 频道id
     * @param type   类型
     * @return 频道
     */
    @Override
    public List<PersonalMsg> getPersonalMsgList(Integer userId, Integer type) {
        List<PersonalMsg> personalMsgList = new ArrayList<>();
        if (type.equals(PersonalMsgUtil.PersonalMsgType.TYPE_FRIEND_PERSONAL_MSG.getCode())) {
            for (PersonalMsgUtil.FriendPersonalMsg friendPersonalMsg : PersonalMsgUtil.FRIEND_PERSONAL_MSG_LIST) {
                personalMsgList.addAll(personalMsgMapper.getPersonalMsgList(userId, friendPersonalMsg.getName()));
            }
        } else if (type.equals(PersonalMsgUtil.PersonalMsgType.TYPE_OTHER_PERSONAL_MSG.getCode())) {
            for (PersonalMsgUtil.GroupPersonalMsg groupPersonalMsg : PersonalMsgUtil.GROUP_PERSONAL_MSG_LIST) {
                personalMsgList.addAll(personalMsgMapper.getPersonalMsgList(userId, groupPersonalMsg.getName()));
            }
            for (PersonalMsgUtil.ChannelPersonalMsg channelPersonalMsg : PersonalMsgUtil.CHANNEL_PERSONAL_MSG_LIST) {
                personalMsgList.addAll(personalMsgMapper.getPersonalMsgList(userId, channelPersonalMsg.getName()));
            }
        }
        return personalMsgList;
    }

    /**
     * 通过用户id获取个人信息通知具体类
     *
     * @param userId 频道id
     * @param type   类型
     * @return 频道
     */
    @Override
    public List<PersonalMsgVO> getPersonalMsgVOList(Integer userId, Integer type) {
        List<PersonalMsg> personalMsgList = getPersonalMsgList(userId, type);
        List<PersonalMsgVO> personalMsgVOList = new ArrayList<>();
        for (PersonalMsg personalMsg : personalMsgList) {
            PersonalMsgVO personalMsgVO = getPersonalMsgVO(personalMsg);
            personalMsgVOList.add(personalMsgVO);
        }
        return personalMsgVOList;
    }

    /**
     * 检查合法性
     *
     * @param type     类型
     * @param userId   用户id
     * @param objectId 好友/群聊/频道id
     * @param typeName 权限类型
     */
    @Override
    public void checkValidity(String type, Integer userId, Integer objectId, String typeName) {
        boolean flag = false;
        if (type.equals(ChatMsgUtil.Character.TYPE_FRIEND.getName())) {
            if (redisService.checkFriendToken(userId, objectId)) {
                flag = true;
            }
        } else if (type.equals(ChatMsgUtil.Character.TYPE_GROUP.getName())) {
            String userValue = redisService.getGroupAuthorityToken(userId, objectId);
            if (!StringUtils.isEmpty(userValue)) {
                if (typeName.equals(ADMIN_ATTRIBUTE)) {
                    groupUserService.authorityCheck(userId, objectId, GROUP_ADMIN_ATTRIBUTE);
                    flag = true;
                } else if (typeName.equals(OWNER_ATTRIBUTE)) {
                    groupUserService.authorityCheck(userId, objectId, GROUP_OWNER_ATTRIBUTE);
                    flag = true;
                } else if (typeName.equals(USER_ATTRIBUTE)) {
                    groupUserService.authorityCheck(userId, objectId, GROUP_USER_ATTRIBUTE);
                    flag = true;
                }
            }
        } else if (type.equals(ChatMsgUtil.Character.TYPE_CHANNEL.getName())) {
            String userValue = redisService.getChannelAuthorityToken(userId, objectId);
            if (!StringUtils.isEmpty(userValue)) {
                if (typeName.equals(OWNER_ATTRIBUTE)) {
                    channelUserService.authorityCheck(userId, objectId, CHANNEL_OWNER_ATTRIBUTE);
                    flag = true;
                } else if (typeName.equals(USER_ATTRIBUTE)) {
                    channelUserService.authorityCheck(userId, objectId, CHANNEL_USER_ATTRIBUTE);
                    flag = true;
                }
            }
        }
        AssertionUtil.notNull(flag, ErrorCode.BIZ_PARAM_ILLEGAL, "操作失败!");
    }

    /**
     * 获取新个人信息个数
     *
     * @param userId 用户id
     * @param type   消息类型
     * @return 信息个数
     */
    @Override
    public Long getNewPersonalMsgListCount(Integer userId, Integer type) {
        User user = userService.getUser(userId);
        user.setPersonalMsgTimestamp(new Date());
        userService.updateUser(user);
        Long cnt = 0L;
        if (type.equals(PersonalMsgUtil.PersonalMsgType.TYPE_FRIEND_PERSONAL_MSG.getCode())) {
            for (PersonalMsgUtil.FriendPersonalMsg friendPersonalMsg : PersonalMsgUtil.FRIEND_PERSONAL_MSG_LIST) {
                cnt += personalMsgMapper.getNewPersonalMsgListCount(userId, friendPersonalMsg.getName());
            }
        } else if (type.equals(PersonalMsgUtil.PersonalMsgType.TYPE_OTHER_PERSONAL_MSG.getCode())) {
            for (PersonalMsgUtil.GroupPersonalMsg groupPersonalMsg : PersonalMsgUtil.GROUP_PERSONAL_MSG_LIST) {
                cnt += personalMsgMapper.getNewPersonalMsgListCount(userId, groupPersonalMsg.getName());
            }
            for (PersonalMsgUtil.ChannelPersonalMsg channelPersonalMsg : PersonalMsgUtil.CHANNEL_PERSONAL_MSG_LIST) {
                cnt += personalMsgMapper.getNewPersonalMsgListCount(userId, channelPersonalMsg.getName());
            }
        }
        return cnt;
    }

    private PersonalMsgVO getPersonalMsgVO(PersonalMsg personalMsg) {
        PersonalMsgVO personalMsgVO = (PersonalMsgVO) personalMsg;
        User user = userService.getUser(personalMsgVO.getOwnerId());
        personalMsgVO.setOwnerNick(user.getNick());
        personalMsgVO.setAvatar(user.getAvatar());
        user = userService.getUser(personalMsgVO.getOperatorId());
        personalMsgVO.setOperatorNick(user.getNick());
        if (PersonalMsgUtil.getFriendPersonalMsgByName(personalMsg.getObjectType()) != null) {
            personalMsgVO.setObjectNick(null);
        } else if (PersonalMsgUtil.getGroupPersonalMsgByName(personalMsg.getObjectType()) != null) {
            Group group = groupService.getGroup(personalMsg.getObjectId());
            personalMsgVO.setObjectNick(group.getNick());
        } else if (PersonalMsgUtil.getChannelPersonalMsgByName(personalMsg.getObjectType()) != null) {
            Channel channel = channelService.getChannel(personalMsg.getObjectId());
            personalMsgVO.setObjectNick(channel.getNick());
        }
        return personalMsgVO;
    }
}
