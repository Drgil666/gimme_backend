package com.project.gimme.mapper;

import com.project.gimme.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @author DrGilbert
 * @date 2022/1/2 12:02
 */
@Mapper
public interface UserMapper {
    /**
     * 创建用户
     *
     * @param user 被创建的用户类
     * @return 是否成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into user (nick, avatar, city, country, occupation, company, " +
            "motto, birthday, mail, province) values " +
            "(#{user.nick},#{user.avatar},#{user.city},#{user.country}," +
            "#{user.occupation},#{user.company},#{user.motto}," +
            "#{user.birthday},#{user.mail},#{user.province})")
    Boolean createUser(@Param("user") User user);
}
