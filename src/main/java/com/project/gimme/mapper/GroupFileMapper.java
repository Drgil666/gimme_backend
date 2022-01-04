package com.project.gimme.mapper;

import com.project.gimme.pojo.GroupFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:58
 */
@Mapper
public interface GroupFileMapper {
    /**
     * 创建群文件
     *
     * @param groupFile 被创建的群文件
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into group_file (owner_id, group_id, file_name, mongo_id)" +
            " values (#{groupFile.ownerId},#{groupFile.groupId}," +
            "#{groupFile.filename},#{groupFile.mongoId})")
    Boolean createGroupFile(@Param("groupFile") GroupFile groupFile);

    /**
     * 更新群文件
     *
     * @param groupFile 要更新的群文件
     * @return 影响行数
     */
    @Update("update group_file set owner_id=#{groupFile.ownerId}," +
            "group_id=#{groupFile.groupId},file_name=#{groupFile.filename}," +
            "mongo_id=#{groupFile.mongoId} where id=#{groupFile.id}")
    Long updateGroupFile(@Param("groupFile") GroupFile groupFile);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from group_file where id=#{id}")
    GroupFile getGroupFile(@Param("id") Integer id);

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param groupId 群聊id
     * @param keyword 文件名
     * @return 查询的用户列表
     */
    @Select("select * from group_file where group_id=#{groupId} and file_name like CONCAT('%',#{keyword},'%')")
    List<GroupFile> getGroupByGroupId(@Param("groupId") Integer groupId, @Param("keyword") String keyword);
}
