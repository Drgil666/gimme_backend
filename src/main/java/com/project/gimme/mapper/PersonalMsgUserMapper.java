package com.project.gimme.mapper;

import com.project.gimme.pojo.PersonalMsgUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/2/3 16:13
 */
@Mapper
public interface PersonalMsgUserMapper {
    /**
     * 创建信息通知用户
     *
     * @param personalMsgUser 要创建的信息通知用户类
     * @return 是否成功
     */
    @Insert("insert into personal_msg_user(personal_msg_id, accept_id) " +
            "values (#{personalMsgUser.personalMsgId},#{personalMsgUser.acceptId})")
    Boolean createPersonalMsgUser(@Param("personalMsgUser") PersonalMsgUser personalMsgUser);

    /**
     * 更新信息通知用户
     *
     * @param personalMsgUser 要更新的信息通知用户类
     * @return 影响行数
     */
    @Update("update personal_msg_user set " +
            "personal_msg_id=#{personalMsgUser.personalMsgId}," +
            "accept_id=#{personalMsgUser.acceptId}")
    Long updatePersonalMsgUser(@Param("personalMsgUser") PersonalMsgUser personalMsgUser);

    /**
     * 根据信息通知id与用户id获取信息通知用户
     *
     * @param personalMsgId 信息通知id
     * @param acceptId      用户id
     * @return 信息通知用户
     */
    @Select("select * from personal_msg_user where " +
            "personal_msg_id=#{personalMsgId} " +
            "and accept_id=#{acceptId}")
    PersonalMsgUser getPersonalMsgUser(@Param("personalMsgId") Integer personalMsgId,
                                       @Param("acceptId") Integer acceptId);

    /**
     * 根据信息通知id获取信息通知用户列表
     *
     * @param personalMsgId 信息通知id
     * @return 信息通知用户列表
     */
    @Select("select * from personal_msg_user where " +
            "personal_msg_id=#{personalMsgId}")
    List<PersonalMsgUser> getPersonalMsgUserList(@Param("personalMsgId") Integer personalMsgId);
}
