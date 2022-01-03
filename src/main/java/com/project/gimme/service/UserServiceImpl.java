package com.project.gimme.service;

import com.project.gimme.mapper.UserMapper;
import com.project.gimme.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/2 13:08
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 创建用户
     *
     * @param user 要创建的用户
     * @return 是否创建成功
     */
    @Override
    public Boolean createUser(User user) {
        return userMapper.createUser(user);
    }

    /**
     * 更新用户
     *
     * @param user 要更新的User
     * @return 更新好的User
     */
    @Override
    public Long updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    @Override
    public List<User> getUserByIdAndNick(String keyword) {
        return userMapper.getUserByIdAndNick(keyword);
    }
}
