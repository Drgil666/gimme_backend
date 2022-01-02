package com.project.gimme.service;

import com.project.gimme.mapper.UserMapper;
import com.project.gimme.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
