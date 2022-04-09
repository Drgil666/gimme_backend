package com.project.gimme.service;

import com.project.gimme.pojo.PersonalMsg;
import com.project.gimme.pojo.vo.PersonalMsgVO;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:54
 */
public interface PersonalMsgService {
    /**
     * 创建信息通知
     *
     * @param personalMsg 被创建的信息通知
     * @return 是否成功
     */
    Boolean createPersonalMsg(PersonalMsg personalMsg);

    /**
     * 创建添加好友个人信息
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @param note     备注
     * @return 个人信息
     */
    PersonalMsg createInsertFriendPersonalMsg(Integer userId, Integer friendId, String note);

    /**
     * 创建删除好友个人信息
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @param note     备注
     * @return 个人信息
     */
    PersonalMsg createDeleteFriendPersonalMsg(Integer userId, Integer friendId);

    /**
     * 创建添加群聊个人信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 个人信息
     */
    PersonalMsg createInsertGroupPersonalMsg(Integer userId, Integer groupId);

    /**
     * 创建删除群聊个人信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 个人信息
     */
    PersonalMsg createDeleteGroupPersonalMsg(Integer userId, Integer groupId);

    /**
     * 创建添加群聊成员个人信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @param note    备注
     * @return 个人信息
     */
    PersonalMsg createInsertGroupMemberPersonalMsg(Integer userId, Integer groupId, String note);

    /**
     * 创建删除群聊成员个人信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 个人信息
     */
    PersonalMsg createDeleteGroupMemberPersonalMsg(Integer userId, Integer groupId);

    /**
     * 创建添加频道个人信息
     *
     * @param userId    用户id
     * @param channelId 频道id
     * @return 个人信息
     */
    PersonalMsg createInsertChannelPersonalMsg(Integer userId, Integer channelId);

    /**
     * 创建删除频道个人信息
     *
     * @param userId    用户id
     * @param channelId 频道id
     * @return 个人信息
     */
    PersonalMsg createDeleteChannelPersonalMsg(Integer userId, Integer channelId);

    /**
     * 创建添加群聊成员个人信息
     *
     * @param userId    用户id
     * @param channelId 频道id
     * @param note      备注
     * @return 个人信息
     */
    PersonalMsg createInsertChannelMemberPersonalMsg(Integer userId, Integer channelId, String note);

    /**
     * 创建删除频道成员个人信息
     *
     * @param userId    用户id
     * @param channelId 群聊id
     * @return 个人信息
     */
    PersonalMsg createDeleteChannelMemberPersonalMsg(Integer userId, Integer channelId);

    /**
     * 更新信息通知
     *
     * @param personalMsg 要更新的信息通知
     * @return 影响行数
     */
    Long updatePersonalMsg(PersonalMsg personalMsg);

    /**
     * 通过id获取个人信息通知
     *
     * @param id 频道id
     * @return 频道
     */
    PersonalMsg getPersonalMsg(Integer id);

    /**
     * 通过id获取个人信息通知
     *
     * @param id 频道id
     * @return 频道
     */
    PersonalMsgVO getPersonalMsgVO(Integer id);

    /**
     * 批量删除通知
     *
     * @param id 删除的id
     * @return 影响行数
     */
    Long deletePersonalMsg(List<Integer> id);

    /**
     * 通过用户id获取个人信息通知
     *
     * @param type   类型
     * @param userId 频道id
     * @return 频道
     */
    List<PersonalMsg> getPersonalMsgList(Integer userId, Integer type);

    /**
     * 通过用户id获取个人信息通知具体类
     *
     * @param userId 频道id
     * @param type   类型
     * @return 频道
     */
    List<PersonalMsgVO> getPersonalMsgVOList(Integer userId, Integer type);

    /**
     * 检查合法性
     *
     * @param type     类型
     * @param userId   用户id
     * @param objectId 好友/群聊/频道id
     * @param typeName 权限类型
     */
    void checkValidity(String type, Integer userId, Integer objectId, String typeName);

    /**
     * 获取新个人信息个数
     *
     * @param userId 用户id
     * @param type   消息类型
     * @return 信息个数
     */
    Long getNewPersonalMsgListCount(Integer userId, Integer type);
}
