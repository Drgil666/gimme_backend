package com.project.gimme.service;

import com.project.gimme.pojo.PersonalMsgUser;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/2/3 16:25
 */
public interface PersonalMsgUserService {
    /**
     * 创建信息通知用户
     *
     * @param personalMsgUser 要创建的信息通知用户类
     * @return 是否成功
     */
    Boolean createPersonalMsgUser(PersonalMsgUser personalMsgUser);

    /**
     * 更新信息通知用户
     *
     * @param personalMsgUser 要更新的信息通知用户类
     * @return 影响行数
     */
    Long updatePersonalMsgUser(PersonalMsgUser personalMsgUser);

    /**
     * 根据信息通知id与用户id获取信息通知用户
     *
     * @param personalMsgId 信息通知id
     * @param acceptId      用户id
     * @return 信息通知用户
     */
    PersonalMsgUser getPersonalMsgUser(Integer personalMsgId,
                                       Integer acceptId);

    /**
     * 根据信息通知id获取信息通知用户列表
     *
     * @param personalMsgId 信息通知id
     * @return 信息通知用户列表
     */
    List<PersonalMsgUser> getPersonalMsgUserList(Integer personalMsgId);

    /**
     * 批量创建群消息个人信息用户
     *
     * @param personalMsgId 信息id
     * @param groupId       群聊id
     */
    void createGroupPersonalMsgUser(Integer personalMsgId, Integer groupId);
}
