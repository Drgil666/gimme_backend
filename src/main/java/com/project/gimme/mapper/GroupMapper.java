package com.project.gimme.mapper;

import com.project.gimme.pojo.Group;
import com.project.gimme.pojo.vo.GroupVO;
import com.project.gimme.pojo.vo.SearchVO;
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
    @Insert("insert into `group` (create_time, nick,description,avatar) values " +
            "(#{group.createTime},#{group.nick},#{group.description},#{group.avatar})")
    Boolean createGroup(@Param("group") Group group);

    /**
     * 更新群聊
     *
     * @param group 要更新的group
     * @return 影响行数
     */
    @Update("update `group` set nick=#{group.nick},description=#{group.description},avatar=#{group.avatar}" +
            " where id=#{group.id}")
    Long updateGroup(@Param("group") Group group);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from `group` where id=#{id}")
    Group getGroup(@Param("id") Integer id);

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    @Select("select * from `group` where id=#{keyword} or nick like CONCAT('%',#{keyword},'%')")
    List<Group> getGroupByIdAndKeyword(@Param("keyword") String keyword);

    /**
     * 根据userId和关键词获取群列表
     *
     * @param userId  用户id
     * @param keyword 关键词
     * @return 群列表
     */
    @Select("select `group`.* from `group`,group_user where " +
            "group_user.user_id=#{userId} and group_user.group_id=`group`.id and `group`.nick like CONCAT('%',#{keyword},'%')")
    List<Group> getGroupList(@Param("userId") Integer userId, @Param("keyword") String keyword);

    /**
     * 如果已加入频道，获取频道信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 频道信息
     */
    @Select("select `group`.*,(select count(*) from group_user where group_id=#{groupId})" +
            "as totalCount,(select group_user.group_nick from group_user where " +
            "group_user.user_id=#{userId} and group_id=#{groupId}) as myNote " +
            "from `group` where `group`.id=#{groupId}")
    GroupVO getGroupVoIfJoin(@Param("userId") Integer userId, @Param("groupId") Integer groupId);

    /**
     * 如果未加入频道，获取频道信息
     *
     * @param groupId 群聊id
     * @return 频道信息
     */
    @Select("select `group`.*,(select count(*) from group_user where group_id=#{groupId})" +
            "as totalCount from `group` where `group`.id=#{groupId}")
    GroupVO getGroupVoIfNotJoin(@Param("groupId") Integer groupId);

    /**
     * 批量删除群聊
     *
     * @param idList 群聊id
     * @return 影响行数
     */
    Long deleteGroupList(@Param("idList") List<Integer> idList);

    /**
     * 删除群聊
     *
     * @param groupId 群聊id
     * @return 影响行数
     */
    @Delete("delete from `group` where id=#{groupId}")
    Long deleteGroup(@Param("groupId") Integer groupId);

    /**
     * 根据关键字查找
     *
     * @param userId     用户id
     * @param searchType 搜索类型
     * @param keyword    关键词
     * @return 查找结果列表
     */
    List<SearchVO> getGroupSearchVoList(@Param("userId") Integer userId,
                                        @Param("searchType") String searchType,
                                        @Param("keyword") String keyword);
}
