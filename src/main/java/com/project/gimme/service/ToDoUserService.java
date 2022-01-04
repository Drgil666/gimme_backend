package com.project.gimme.service;

import com.project.gimme.pojo.ToDoUser;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:47
 */
public interface ToDoUserService {
    /**
     * 创建待办成员
     *
     * @param toDoUser 被创建的待办成员关系
     * @return 是否成功
     */
    Boolean createToDoUser(ToDoUser toDoUser);

    /**
     * 更新待办成员
     *
     * @param toDoUser 要更新的待办成员
     * @return 影响行数
     */
    Long updateToDoUser(ToDoUser toDoUser);

    /**
     * 通过id获取待办成员
     *
     * @param userId 用户id
     * @param toDoId 待办id
     * @return 待办成员
     */
    ToDoUser getToDoUser(Integer toDoId, Integer userId);

    /**
     * 批量删除待办成员
     *
     * @param toDoId     待办id
     * @param userIdList 用户id列表
     * @return 影响行数
     */
    Long deleteGroupUser(Integer toDoId, List<Integer> userIdList);
}
