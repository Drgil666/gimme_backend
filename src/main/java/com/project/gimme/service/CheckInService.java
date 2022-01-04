package com.project.gimme.service;

import com.project.gimme.pojo.CheckIn;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:16
 */
public interface CheckInService {
    /**
     * 创建签到
     *
     * @param checkIn 被创建的签到类
     * @return 是否成功
     */
    Boolean createCheckIn(CheckIn checkIn);

    /**
     * 更新签到
     *
     * @param checkIn 要更新的签到
     * @return 影响行数
     */
    Long updateCheckIn(CheckIn checkIn);

    /**
     * 通过id获取签到信息
     *
     * @param id 用户id
     * @return 用户
     */
    CheckIn getCheckIn(Integer id);
}
