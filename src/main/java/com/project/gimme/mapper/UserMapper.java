package com.project.gimme.mapper;

import com.project.gimme.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
            "motto, birthday, mail, province,gender) values " +
            "(#{user.nick},#{user.avatar},#{user.city},#{user.country}," +
            "#{user.occupation},#{user.company},#{user.motto}," +
            "#{user.birthday},#{user.mail},#{user.province},#{user.gender})")
    Boolean createUser(@Param("user") User user);

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 影响行数
     */
    @Update("update user set nick=#{user.nick},avatar=#{user.avatar},city=#{user.city}," +
            "country=#{user.country},occupation=#{user.occupation},company=#{user.company}," +
            "motto=#{user.motto},birthday=#{user.birthday},mail=#{user.mail}," +
            "province=#{user.province},gender=#{user.gender} where id=#{user.id}")
    Long updateUser(@Param("user") User user);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from user where id=#{id}")
    User getUser(@Param("id") Integer id);

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    @Select("select * from user where id=#{keyword} or nick like CONCAT('%',#{keyword},'%')")
    List<User> getUserByIdAndNick(@Param("keyword") String keyword);
}
