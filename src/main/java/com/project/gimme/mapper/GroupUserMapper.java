package com.project.gimme.mapper;

import com.project.gimme.pojo.GroupUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/3 10:52
 */
@Mapper
public interface GroupUserMapper {
    /**
     * 创建群聊成员
     *
     * @param groupUser 被创建的群聊成员关系
     * @return 是否成功
     */
    @Insert("insert into group_user (group_id, user_id, type,group_nick) values " +
            "(#{groupUser.groupId},#{groupUser.userId},#{groupUser.type}," +
            "#{groupUser.groupNick})")
    Boolean createGroupUser(@Param("groupUser") GroupUser groupUser);

    /**
     * 更新群聊成员权限
     *
     * @param groupUser 要更新的groupUser
     * @return 影响行数
     */
    @Update("update group_user set type=#{groupUser.type},group_nick=#{groupUser.groupNick} where user_id=#{groupUser.userId} " +
            "and group_id=#{groupUser.groupId}")
    Long updateGroupUser(@Param("groupUser") GroupUser groupUser);

    /**
     * 通过id获取群聊成员
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 用户
     */
    @Select("select * from group_user where group_id=#{groupId} and user_id=#{userId}")
    GroupUser getGroupUser(@Param("groupId") Integer groupId, @Param("userId") Integer userId);

    /**
     * 批量删除群聊成员
     *
     * @param groupId    群聊id
     * @param userIdList 用户id列表
     * @return 影响行数
     */
    Long deleteGroupUser(@Param("groupId") Integer groupId, @Param("userId") List<Integer> userIdList);

    /**
     * 获取人员列表
     *
     * @param groupId 群聊id
     * @param type    权限类型
     * @return
     */
    @Select("select user_id from group_user where group_id=#{groupId} and type<=#{type}")
    List<Integer> getGroupAdminList(Integer groupId, Integer type);
}
