package com.project.gimme.service;

import com.project.gimme.pojo.User;

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
}
