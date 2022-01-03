package com.project.gimme.mapper;

import com.project.gimme.pojo.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/3 10:34
 */
@Mapper
public interface GroupMapper {
    /**
     * 创建群聊
     *
     * @param group 被创建的群聊类
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into group (create_time, nick) values " +
            "(#{group.create_time},#{group.nick})")
    Boolean createGroup(@Param("group") Group group);

    /**
     * 更新群聊
     *
     * @param group 要更新的group
     * @return 影响行数
     */
    @Update("update user set nick=#{group.nick} where id=#{group.id}")
    Long updateGroup(@Param("group") Group group);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from group where id=#{id}")
    Group getGroup(@Param("id") Integer id);

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    @Select("select * from group where id=#{keyword} or nick like CONCAT('%',#{keyword},'%')")
    List<Group> getGroupByIdAndNick(@Param("keyword") String keyword);
}
