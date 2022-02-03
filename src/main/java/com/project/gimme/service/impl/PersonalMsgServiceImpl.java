package com.project.gimme.service.impl;

import com.project.gimme.mapper.PersonalMsgMapper;
import com.project.gimme.pojo.PersonalMsg;
import com.project.gimme.pojo.vo.PersonalMsgVO;
import com.project.gimme.service.PersonalMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:54
 */
@Service
public class PersonalMsgServiceImpl implements PersonalMsgService {
    @Resource
    private PersonalMsgMapper personalMsgMapper;

    /**
     * 创建信息通知
     *
     * @param personalMsg 被创建的信息通知
     * @return 是否成功
     */
    @Override
    public Boolean createPersonalMsg(PersonalMsg personalMsg) {
        return personalMsgMapper.createPersonalMsg(personalMsg);
    }

    /**
     * 更新信息通知
     *
     * @param personalMsg 要更新的信息通知
     * @return 影响行数
     */
    @Override
    public Long updatePersonalMsg(PersonalMsg personalMsg) {
        return personalMsgMapper.updatePersonalMsg(personalMsg);
    }

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    @Override
    public PersonalMsg getPersonalMsg(Integer id) {
        return personalMsgMapper.getPersonalMsg(id);
    }

    /**
     * 通过id获取个人信息通知
     *
     * @param id 频道id
     * @return 频道
     */
    @Override
    public PersonalMsgVO getPersonalMsgVO(Integer id) {
        return null;
    }

    /**
     * 批量删除通知
     *
     * @param id 删除的id
     * @return 影响行数
     */
    @Override
    public Long deletePersonalMsg(List<Integer> id) {
        return personalMsgMapper.deletePersonalMsg(id);
    }

    /**
     * 通过用户id获取个人信息通知
     *
     * @param userId 频道id
     * @return 频道
     */
    @Override
    public List<PersonalMsg> getPersonalMsgList(Integer userId) {
        return personalMsgMapper.getPersonalMsgList(userId);
    }
}
