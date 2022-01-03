package com.project.gimme.service;

import com.project.gimme.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/2 13:07
 */
public interface UserService {
    /**
     * 创建用户
     *
     * @param user 要创建的用户
     * @return 是否创建成功
     */
    Boolean createUser(User user);

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 更新好的User
     */
    Long updateUser(User user);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    User getUser(Integer id);

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    List<User> getUserByIdAndNick(@Param("keyword") String keyword);
}
