package com.project.gimme.service.impl;

import com.project.gimme.mapper.PersonalMsgUserMapper;
import com.project.gimme.pojo.PersonalMsgUser;
import com.project.gimme.service.ChannelUserService;
import com.project.gimme.service.GroupUserService;
import com.project.gimme.service.PersonalMsgUserService;
import com.project.gimme.utils.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/2/3 16:26
 */
@Service
public class PersonalMsgUserServiceImpl implements PersonalMsgUserService {
    @Resource
    private PersonalMsgUserMapper personalMsgUserMapper;
    @Resource
    private GroupUserService groupUserService;
    @Resource
    private ChannelUserService channelUserService;

    /**
     * 创建信息通知用户
     *
     * @param personalMsgUser 要创建的信息通知用户类
     * @return 是否成功
     */
    @Override
    public Boolean createPersonalMsgUser(PersonalMsgUser personalMsgUser) {
        return personalMsgUserMapper.createPersonalMsgUser(personalMsgUser);
    }

    /**
     * 更新信息通知用户
     *
     * @param personalMsgUser 要更新的信息通知用户类
     * @return 影响行数
     */
    @Override
    public Long updatePersonalMsgUser(PersonalMsgUser personalMsgUser) {
        return personalMsgUserMapper.updatePersonalMsgUser(personalMsgUser);
    }

    /**
     * 根据信息通知id与用户id获取信息通知用户
     *
     * @param personalMsgId 信息通知id
     * @param acceptId      用户id
     * @return 信息通知用户
     */
    @Override
    public PersonalMsgUser getPersonalMsgUser(Integer personalMsgId, Integer acceptId) {
        return personalMsgUserMapper.getPersonalMsgUser(personalMsgId, acceptId);
    }

    /**
     * 根据信息通知id获取信息通知用户列表
     *
     * @param personalMsgId 信息通知id
     * @return 信息通知用户列表
     */
    @Override
    public List<PersonalMsgUser> getPersonalMsgUserList(Integer personalMsgId) {
        return personalMsgUserMapper.getPersonalMsgUserList(personalMsgId);
    }

    /**
     * 批量创建群消息个人信息用户
     *
     * @param personalMsgId 信息id
     * @param groupId       群聊id
     */
    @Override
    public void createGroupPersonalMsgUser(Integer personalMsgId, Integer groupId) {
        List<Integer> idList = groupUserService.getGroupAdminList(groupId, UserUtil.GroupCharacter.TYPE_GROUP_ADMIN.getCode());
        for (Integer id : idList) {
            PersonalMsgUser personalMsgUser = new PersonalMsgUser();
            personalMsgUser.setPersonalMsgId(personalMsgId);
            personalMsgUser.setAcceptId(id);
            personalMsgUserMapper.createPersonalMsgUser(personalMsgUser);
        }
    }
}
