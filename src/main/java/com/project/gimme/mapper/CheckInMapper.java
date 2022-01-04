package com.project.gimme.mapper;

import com.project.gimme.pojo.CheckIn;
import org.apache.ibatis.annotations.*;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:13
 */
@Mapper
public interface CheckInMapper {
    /**
     * 创建签到
     *
     * @param checkIn 被创建的签到类
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into checkin (group_id, type, address) values " +
            "(#{checkIn.groupId},#{checkIn.type},#{checkIn.address})")
    Boolean createCheckIn(@Param("checkIn") CheckIn checkIn);

    /**
     * 更新签到
     *
     * @param checkIn 要更新的签到
     * @return 影响行数
     */
    @Update("update checkin set group_id=#{checkIn.groupId}," +
            "type=#{checkIn.type},address=#{checkIn.address}" +
            " where id=#{checkIn.id}")
    Long updateCheckIn(@Param("checkIn") CheckIn checkIn);

    /**
     * 通过id获取签到信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from checkin where id=#{id}")
    CheckIn getCheckIn(@Param("id") Integer id);
}
