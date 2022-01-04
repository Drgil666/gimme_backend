package com.project.gimme.mapper;

import com.project.gimme.pojo.PersonalMsg;
import org.apache.ibatis.annotations.*;

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
    @Insert("insert into personal_msg (type, owner_id, operator_id, object_id) " +
            "values (#{personalMsg.type},#{personalMsg.ownerId}," +
            "#{personalMsg.operatorId},#{personalMsg.objectId})")
    Boolean createPersonalMsg(@Param("personalMsg") PersonalMsg personalMsg);

    /**
     * 更新信息通知
     *
     * @param personalMsg 要更新的信息通知
     * @return 影响行数
     */
    @Update("update personal_msg set type=#{personalMsg.type}," +
            "owner_id=#{personalMsg.ownerId},operator_id=#{personalMsg.operatorId}," +
            "object_id=#{personalMsg.objectId} " +
            "where id=#{personalMsg.id}")
    Long updatePersonalMsg(@Param("personalMsg") PersonalMsg personalMsg);

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    @Select("select * from personal_msg where id=#{id}")
    PersonalMsg getPersonalMsg(@Param("id") Integer id);
}
