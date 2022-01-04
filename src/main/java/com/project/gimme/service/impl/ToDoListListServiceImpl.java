package com.project.gimme.service.impl;

import com.project.gimme.mapper.ToDoListMapper;
import com.project.gimme.pojo.ToDoList;
import com.project.gimme.service.ToDoListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:22
 */
@Service
public class ToDoListListServiceImpl implements ToDoListService {
    @Resource
    private ToDoListMapper toDoListMapper;

    /**
     * 创建待办
     *
     * @param toDoList 被创建的待办
     * @return 是否成功
     */
    @Override
    public Boolean createTodoList(ToDoList toDoList) {
        return toDoListMapper.createTodoList(toDoList);
    }

    /**
     * 更新待办
     *
     * @param toDoList 要更新的待办
     * @return 影响行数
     */
    @Override
    public Long updateToDoList(ToDoList toDoList) {
        return toDoListMapper.updateToDoList(toDoList);
    }

    /**
     * 通过id获取待办
     *
     * @param id 频道id
     * @return 频道
     */
    @Override
    public ToDoList getTodoList(Integer id) {
        return toDoListMapper.getTodoList(id);
    }

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    @Override
    public Long deleteTodoList(List<Integer> idList) {
        return toDoListMapper.deleteTodoList(idList);
    }
}
