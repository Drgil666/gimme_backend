package com.project.gimme.service;

import com.project.gimme.pojo.CheckInUser;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:08
 */
public interface CheckInUserService {
    /**
     * 创建签到成员
     *
     * @param checkInUser 被创建的签到成员类
     * @return 是否成功
     */
    Boolean createCheckInUser(CheckInUser checkInUser);

    /**
     * 更新签到成员
     *
     * @param checkInUser 要更新的签到成员
     * @return 影响行数
     */
    Long updateCheckInUser(CheckInUser checkInUser);

    /**
     * 通过id获取签到成员
     *
     * @param checkInId 签到id
     * @param userId    用户id
     * @return 频道成员
     */
    CheckInUser getCheckInUser(Integer checkInId, Integer userId);
}
