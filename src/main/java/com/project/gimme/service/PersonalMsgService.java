package com.project.gimme.service;

import com.project.gimme.pojo.PersonalMsg;
import com.project.gimme.pojo.vo.PersonalMsgVO;

import java.util.List;

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
     * 通过id获取个人信息通知
     *
     * @param id 频道id
     * @return 频道
     */
    PersonalMsg getPersonalMsg(Integer id);

    /**
     * 通过id获取个人信息通知
     *
     * @param id 频道id
     * @return 频道
     */
    PersonalMsgVO getPersonalMsgVO(Integer id);

    /**
     * 批量删除通知
     *
     * @param id 删除的id
     * @return 影响行数
     */
    Long deletePersonalMsg(List<Integer> id);

    /**
     * 通过用户id获取个人信息通知
     *
     * @param userId 频道id
     * @return 频道
     */
    List<PersonalMsg> getPersonalMsgList(Integer userId);

    /**
     * 通过用户id获取个人信息通知具体类
     *
     * @param userId 频道id
     * @return 频道
     */
    List<PersonalMsgVO> getPersonalMsgVOList(Integer userId);

    /**
     * 检查合法性
     *
     * @param type     类型
     * @param userId   用户id
     * @param objectId 好友/群聊/频道id
     * @param typeName 权限类型
     */
    void checkValidity(String type, Integer userId, Integer objectId, String typeName);

    /**
     * 获取新个人信息个数
     *
     * @param userId 用户id
     * @param type   消息类型
     * @return 信息个数
     */
    Long getNewPersonalMsgListCount(Integer userId, Integer type);
}
