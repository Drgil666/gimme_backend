package com.project.gimme.service;

import com.project.gimme.pojo.PersonalMsg;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:54
 */
public interface PersonalMsgService {
    /**
     * 创建信息通知
     *
     * @param personalMsg 被创建的信息通知
     * @return 是否成功
     */
    Boolean createPersonalMsg(PersonalMsg personalMsg);

    /**
     * 更新信息通知
     *
     * @param personalMsg 要更新的信息通知
     * @return 影响行数
     */
    Long updatePersonalMsg(PersonalMsg personalMsg);

    /**
     * 通过id获取频道
     *
     * @param id 频道id
     * @return 频道
     */
    PersonalMsg getPersonalMsg(Integer id);
}
