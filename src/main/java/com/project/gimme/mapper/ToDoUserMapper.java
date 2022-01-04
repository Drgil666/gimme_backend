package com.project.gimme.mapper;

import com.project.gimme.pojo.ToDoUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:41
 */
@Mapper
public interface ToDoUserMapper {
    /**
     * 创建待办成员
     *
     * @param toDoUser 被创建的待办成员关系
     * @return 是否成功
     */
    @Insert("insert into todo_user (todo_id, user_id, status) values " +
            "(#{toDoUser.toDoId},#{toDoUser.userId},#{toDoUser.status})")
    Boolean createToDoUser(@Param("toDoUser") ToDoUser toDoUser);

    /**
     * 更新待办成员
     *
     * @param toDoUser 要更新的待办成员
     * @return 影响行数
     */
    @Update("update todo_user set status=#{toDoUser.status} " +
            "where user_id=#{toDoUser.userId} " +
            "and todo_id=#{toDoUser.toDoId}")
    Long updateToDoUser(@Param("toDoUser") ToDoUser toDoUser);

    /**
     * 通过id获取待办成员
     *
     * @param userId 用户id
     * @param toDoId 待办id
     * @return 待办成员
     */
    @Select("select * from todo_user where " +
            "todo_id=#{toDoId} and user_id=#{userId}")
    ToDoUser getToDoUser(@Param("toDoId") Integer toDoId,
                         @Param("userId") Integer userId);

    /**
     * 批量删除待办成员
     *
     * @param toDoId     待办id
     * @param userIdList 用户id列表
     * @return 影响行数
     */
    Long deleteGroupUser(@Param("toDoId") Integer toDoId,
                         @Param("userId") List<Integer> userIdList);

}
