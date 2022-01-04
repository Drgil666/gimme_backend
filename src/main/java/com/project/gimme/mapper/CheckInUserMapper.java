package com.project.gimme.mapper;

import com.project.gimme.pojo.CheckInUser;
import org.apache.ibatis.annotations.*;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:04
 */
@Mapper
public interface CheckInUserMapper {
    /**
     * 创建签到成员
     *
     * @param checkInUser 被创建的签到成员类
     * @return 是否成功
     */
    @Insert("insert into checkin_user (user_id, checkin_id, timestamp) values " +
            "(#{checkInUser.userId},#{checkInUser.checkInId}," +
            "#{checkInUser.timestamp})")
    Boolean createCheckInUser(@Param("checkInUser") CheckInUser checkInUser);

    /**
     * 更新签到成员
     *
     * @param checkInUser 要更新的签到成员
     * @return 影响行数
     */
    @Update("update checkin_user set timestamp=#{checkInUser.timestamp} " +
            "where checkin_id=#{checkInUser.checkInId} and " +
            "user_id=#{checkInUser.userId}")
    Long updateCheckInUser(@Param("checkInUser") CheckInUser checkInUser);

    /**
     * 通过id获取签到成员
     *
     * @param checkInId 签到id
     * @param userId    用户id
     * @return 频道成员
     */
    @Select("select * from checkin_user where " +
            "checkin_id=#{checkInId} and user_id=#{userId}")
    CheckInUser getCheckInUser(@Param("checkInId") Integer checkInId,
                               @Param("userId") Integer userId);
}
