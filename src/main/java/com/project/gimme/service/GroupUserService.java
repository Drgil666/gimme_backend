package com.project.gimme.service;

import com.project.gimme.pojo.GroupUser;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/3 11:14
 */
public interface GroupUserService {
    /**
     * 创建群聊成员
     *
     * @param groupUser 被创建的群聊成员关系
     * @return 是否成功
     */
    Boolean createGroupUser(GroupUser groupUser);

    /**
     * 更新群聊成员权限
     *
     * @param groupUser 要更新的groupUser
     * @return 影响行数
     */
    Long updateGroupUser(GroupUser groupUser);

    /**
     * 通过id获取群聊成员
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 用户
     */
    GroupUser getGroupUser(Integer groupId, Integer userId);

    /**
     * 批量删除群聊成员
     *
     * @param groupId    群聊id
     * @param userIdList 用户id列表
     * @return 影响行数
     */
    Long deleteGroupUser(Integer groupId, List<Integer> userIdList);

    /**
     * 判断是否有权限
     *
     * @param userId   用户id
     * @param groupId  群聊id
     * @param typeName 类型id
     * @return 是否有权限
     */
    Boolean authorityCheck(Integer userId, Integer groupId, String typeName);
}
