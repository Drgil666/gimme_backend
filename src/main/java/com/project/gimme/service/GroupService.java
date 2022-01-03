package com.project.gimme.service;

import com.project.gimme.pojo.Group;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/3 10:43
 */
public interface GroupService {
    /**
     * 创建群聊
     *
     * @param group 被创建的群聊类
     * @return 是否成功
     */
    Boolean createGroup(Group group);

    /**
     * 更新群聊
     *
     * @param group 要更新的group
     * @return 影响行数
     */
    Long updateGroup(Group group);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    Group getGroup(Integer id);

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    List<Group> getGroupByIdAndNick(String keyword);
}
