package com.project.gimme.service;

import com.project.gimme.pojo.ToDoList;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:22
 */
public interface ToDoListService {
    /**
     * 创建待办
     *
     * @param toDoList 被创建的待办
     * @return 是否成功
     */
    Boolean createTodoList(ToDoList toDoList);

    /**
     * 更新待办
     *
     * @param toDoList 要更新的待办
     * @return 影响行数
     */
    Long updateToDoList(ToDoList toDoList);

    /**
     * 通过id获取待办
     *
     * @param id 频道id
     * @return 频道
     */
    ToDoList getTodoList(Integer id);

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteTodoList(List<Integer> idList);
}
