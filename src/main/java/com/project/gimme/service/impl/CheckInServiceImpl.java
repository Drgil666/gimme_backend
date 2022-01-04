package com.project.gimme.service.impl;

import com.project.gimme.mapper.CheckInMapper;
import com.project.gimme.pojo.CheckIn;
import com.project.gimme.service.CheckInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:17
 */
@Service
public class CheckInServiceImpl implements CheckInService {
    @Resource
    private CheckInMapper checkInMapper;

    /**
     * 创建签到
     *
     * @param checkIn 被创建的签到类
     * @return 是否成功
     */
    @Override
    public Boolean createCheckIn(CheckIn checkIn) {
        return checkInMapper.createCheckIn(checkIn);
    }

    /**
     * 更新签到
     *
     * @param checkIn 要更新的签到
     * @return 影响行数
     */
    @Override
    public Long updateCheckIn(CheckIn checkIn) {
        return checkInMapper.updateCheckIn(checkIn);
    }

    /**
     * 通过id获取签到信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public CheckIn getCheckIn(Integer id) {
        return checkInMapper.getCheckIn(id);
    }
}
