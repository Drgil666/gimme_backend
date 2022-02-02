package com.project.gimme.service.impl;

import com.project.gimme.mapper.GroupMapper;
import com.project.gimme.pojo.Group;
import com.project.gimme.pojo.vo.GroupVO;
import com.project.gimme.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/3 10:43
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupMapper groupMapper;

    /**
     * 创建群聊
     *
     * @param group 被创建的群聊类
     * @return 是否成功
     */
    @Override
    public Boolean createGroup(Group group) {
        return groupMapper.createGroup(group);
        //ToDoList:要创建群聊成员进去
    }

    /**
     * 更新群聊
     *
     * @param group 要更新的group
     * @return 影响行数
     */
    @Override
    public Long updateGroup(Group group) {
        return groupMapper.updateGroup(group);
    }

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public Group getGroup(Integer id) {
        return groupMapper.getGroup(id);
    }

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    @Override
    public List<Group> getGroupByIdAndKeyword(String keyword) {
        return groupMapper.getGroupByIdAndKeyword(keyword);
    }

    /**
     * 如果已加入频道，获取频道信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 频道信息
     */
    @Override
    public GroupVO getGroupVoIfJoin(Integer userId, Integer groupId) {
        return groupMapper.getGroupVoIfJoin(userId, groupId);
    }

    /**
     * 如果未加入频道，获取频道信息
     *
     * @param groupId 群聊id
     * @return 频道信息
     */
    @Override
    public GroupVO getGroupVoIfNotJoin(Integer groupId) {
        return groupMapper.getGroupVoIfNotJoin(groupId);
    }
}
