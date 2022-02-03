package com.project.gimme.mapper;

import com.project.gimme.pojo.PersonalMsg;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:51
 */
@Mapper
public interface PersonalMsgMapper {
    /**
     * 创建信息通知
     *
     * @param personalMsg 被创建的信息通知
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into personal_msg (type, owner_id, operator_id, object_id,note,status) " +
            "values (#{personalMsg.type},#{personalMsg.ownerId}," +
            "#{personalMsg.operatorId},#{personalMsg.objectId}," +
            "#{personalMsg.note},#{personalMsg.status})")
    Boolean createPersonalMsg(@Param("personalMsg") PersonalMsg personalMsg);

    /**
     * 更新信息通知
     *
     * @param personalMsg 要更新的信息通知
     * @return 影响行数
     */
    @Update("update personal_msg set type=#{personalMsg.type}," +
            "owner_id=#{personalMsg.ownerId},operator_id=#{personalMsg.operatorId}," +
            "object_id=#{personalMsg.objectId},note=#{personalMsg.note},status=#{personalMsg.status} " +
            "where id=#{personalMsg.id}")
    Long updatePersonalMsg(@Param("personalMsg") PersonalMsg personalMsg);

    /**
     * 通过id获取个人信息通知
     *
     * @param id 频道id
     * @return 频道
     */
    @Select("select * from personal_msg where id=#{id}")
    PersonalMsg getPersonalMsg(@Param("id") Integer id);

    /**
     * 批量删除通知
     *
     * @param id 删除的id
     * @return 影响行数
     */
    Long deletePersonalMsg(@Param("idList") List<Integer> id);

    /**
     * 通过用户id获取个人信息通知
     *
     * @param userId 频道id
     * @return 频道
     */
    @Select("select personal_msg.* from personal_msg,personal_msg_user " +
            "where personal_msg_user.accept_id=#{userId} " +
            "and personal_msg_user.personal_msg_id=personal_msg.id")
    List<PersonalMsg> getPersonalMsgList(@Param("userId") Integer userId);
}
