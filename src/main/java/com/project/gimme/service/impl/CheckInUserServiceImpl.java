package com.project.gimme.service.impl;

import com.project.gimme.mapper.CheckInUserMapper;
import com.project.gimme.pojo.CheckInUser;
import com.project.gimme.service.CheckInUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:08
 */
@Service
public class CheckInUserServiceImpl implements CheckInUserService {
    @Resource
    private CheckInUserMapper checkInUserMapper;

    /**
     * 创建签到成员
     *
     * @param checkInUser 被创建的签到成员类
     * @return 是否成功
     */
    @Override
    public Boolean createCheckInUser(CheckInUser checkInUser) {
        return checkInUserMapper.createCheckInUser(checkInUser);
    }

    /**
     * 更新签到成员
     *
     * @param checkInUser 要更新的签到成员
     * @return 影响行数
     */
    @Override
    public Long updateCheckInUser(CheckInUser checkInUser) {
        return checkInUserMapper.updateCheckInUser(checkInUser);
    }

    /**
     * 通过id获取签到成员
     *
     * @param checkInId 签到id
     * @param userId    用户id
     * @return 频道成员
     */
    @Override
    public CheckInUser getCheckInUser(Integer checkInId, Integer userId) {
        return checkInUserMapper.getCheckInUser(checkInId, userId);
    }
}
