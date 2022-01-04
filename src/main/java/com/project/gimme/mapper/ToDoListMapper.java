package com.project.gimme.mapper;

import com.project.gimme.pojo.ToDoList;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:18
 */
@Mapper
public interface ToDoListMapper {
    /**
     * 创建待办
     *
     * @param toDoList 被创建的待办
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into todo_list (owner_id, text, start_time, end_time) values " +
            "(#{toDoList.ownerId},#{toDoList.text},#{toDoList.startTime},#{toDoList.endTime})")
    Boolean createTodoList(@Param("ToDoList") ToDoList toDoList);

    /**
     * 更新待办
     *
     * @param toDoList 要更新的待办
     * @return 影响行数
     */
    @Update("update todo_list set owner_id=#{toDoList.ownerId}," +
            "text=#{toDoList.text},start_time=#{toDoList.startTime}," +
            "end_time=#{toDoList.endTime} " +
            "where id=#{toDoList.id}")
    Long updateToDoList(@Param("toDo") ToDoList toDoList);

    /**
     * 通过id获取待办
     *
     * @param id 频道id
     * @return 频道
     */
    @Select("select * from todo_list where id=#{id}")
    ToDoList getTodoList(@Param("id") Integer id);

    /**
     * 批量删除频道
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteTodoList(@Param("id") List<Integer> idList);
}
