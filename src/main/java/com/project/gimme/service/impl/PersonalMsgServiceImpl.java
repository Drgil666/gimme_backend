package com.project.gimme.service.impl;

import com.project.gimme.exception.ErrorCode;
import com.project.gimme.mapper.PersonalMsgMapper;
import com.project.gimme.pojo.Channel;
import com.project.gimme.pojo.Group;
import com.project.gimme.pojo.PersonalMsg;
import com.project.gimme.pojo.User;
import com.project.gimme.pojo.vo.PersonalMsgVO;
import com.project.gimme.service.*;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.ChatMsgUtil;
import com.project.gimme.utils.PersonalMsgUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
     * @return 频道
     */
    @Override
    public List<PersonalMsg> getPersonalMsgList(Integer userId) {
        return personalMsgMapper.getPersonalMsgList(userId);
    }

    /**
     * 通过用户id获取个人信息通知具体类
     *
     * @param userId 频道id
     * @return 频道
     */
    @Override
    public List<PersonalMsgVO> getPersonalMsgVOList(Integer userId) {
        List<PersonalMsg> personalMsgList = personalMsgMapper.getPersonalMsgList(userId);
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
