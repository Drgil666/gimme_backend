package com.project.gimme.mapper;

import com.project.gimme.pojo.GroupNotice;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 9:38
 */
@Mapper
public interface GroupNoticeMapper {
    /**
     * 创建群公告
     *
     * @param groupNotice 被创建的群公告
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into group_notice (owner_id, group_id,text) values " +
            "(#{groupNotice.ownerId},#{groupNotice.groupId},#{groupNotice.text})")
    Boolean createGroupNotice(@Param("groupNotice") GroupNotice groupNotice);

    /**
     * 更新群公告
     *
     * @param groupNotice 要更新的群公告
     * @return 影响行数
     */
    @Update("update group_notice set owner_id=#{groupNotice.ownerId}," +
            "group_id=#{groupNotice.groupId},text=#{groupNotice.text} where id=#{groupNotice.id}")
    Long updateGroupNotice(@Param("groupNotice") GroupNotice groupNotice);

    /**
     * 通过id获取群公告
     *
     * @param id 群公告id
     * @return 群公告
     */
    @Select("select * from group_notice where id=#{id}")
    GroupNotice getGroupNotice(@Param("id") Integer id);

    /**
     * 根据群id获取群公告列表
     *
     * @param groupId 群id
     * @return 群公告列表
     */
    @Select("select * from group_notice where group_id=#{groupId}")
    List<GroupNotice> getGroupNoticeList(@Param("groupId") Integer groupId);

    /**
     * 批量删除群公告
     *
     * @param idList id列表
     * @return 影响行数
     */
    Long deleteGroupNotice(@Param("id") List<Integer> idList);
}
