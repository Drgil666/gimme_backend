package com.project.gimme.service.impl;

import com.project.gimme.mapper.GroupUserMapper;
import com.project.gimme.pojo.GroupUser;
import com.project.gimme.service.GroupUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/3 11:15
 */
@Service
public class GroupUserServiceImpl implements GroupUserService {
    @Resource
    private GroupUserMapper groupUserMapper;
    //ToDoList:外键异常捕获

    /**
     * 创建群聊成员
     *
     * @param groupUser 被创建的群聊成员关系
     * @return 是否成功
     */
    @Override
    public Boolean createGroupUser(GroupUser groupUser) {
        return groupUserMapper.createGroupUser(groupUser);
    }

    /**
     * 更新群聊成员权限
     *
     * @param groupUser 要更新的groupUser
     * @return 影响行数
     */
    @Override
    public Long updateGroupUser(GroupUser groupUser) {
        return groupUserMapper.updateGroupUser(groupUser);
    }

    /**
     * 通过id获取群聊成员
     *
     * @param groupId 群聊id
     * @param userId  用户id
     * @return 用户
     */
    @Override
    public GroupUser getGroupUser(Integer groupId, Integer userId) {
        return groupUserMapper.getGroupUser(groupId, userId);
    }

    /**
     * 批量删除群聊成员
     *
     * @param groupId    群聊id
     * @param userIdList 用户id列表
     * @return 影响行数
     */
    @Override
    public Long deleteGroupUser(Integer groupId, List<Integer> userIdList) {
        return groupUserMapper.deleteGroupUser(groupId, userIdList);
    }
}
