package com.project.gimme.service.impl;

import com.project.gimme.mapper.ToDoUserMapper;
import com.project.gimme.pojo.ToDoUser;
import com.project.gimme.service.ToDoUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:48
 */
@Service
public class ToDoUserServiceImpl implements ToDoUserService {
    @Resource
    private ToDoUserMapper toDoUserMapper;

    /**
     * 创建待办成员
     *
     * @param toDoUser 被创建的待办成员关系
     * @return 是否成功
     */
    @Override
    public Boolean createToDoUser(ToDoUser toDoUser) {
        return toDoUserMapper.createToDoUser(toDoUser);
    }

    /**
     * 更新待办成员
     *
     * @param toDoUser 要更新的待办成员
     * @return 影响行数
     */
    @Override
    public Long updateToDoUser(ToDoUser toDoUser) {
        return toDoUserMapper.updateToDoUser(toDoUser);
    }

    /**
     * 通过id获取待办成员
     *
     * @param toDoId 待办id
     * @param userId 用户id
     * @return 待办成员
     */
    @Override
    public ToDoUser getToDoUser(Integer toDoId, Integer userId) {
        return toDoUserMapper.getToDoUser(toDoId, userId);
    }

    /**
     * 批量删除待办成员
     *
     * @param toDoId     待办id
     * @param userIdList 用户id列表
     * @return 影响行数
     */
    @Override
    public Long deleteGroupUser(Integer toDoId, List<Integer> userIdList) {
        return toDoUserMapper.deleteGroupUser(toDoId, userIdList);
    }
}
