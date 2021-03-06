package com.project.gimme.service;

import com.project.gimme.pojo.Group;
import com.project.gimme.pojo.vo.GroupVO;
import com.project.gimme.pojo.vo.SearchVO;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/3 10:43
 */
public interface GroupService {
    /**
     * 创建群聊
     *
     * @param group 被创建的群聊类
     * @return 是否成功
     */
    Boolean createGroup(Group group);

    /**
     * 更新群聊
     *
     * @param group 要更新的group
     * @return 影响行数
     */
    Long updateGroup(Group group);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    Group getGroup(Integer id);

    /**
     * 根据id或昵称查询用户列表
     *
     * @param keyword 关键词
     * @return 查询的用户列表
     */
    List<Group> getGroupByIdAndKeyword(String keyword);

    /**
     * 如果已加入频道，获取频道信息
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return 频道信息
     */
    GroupVO getGroupVoIfJoin(Integer userId, Integer groupId);

    /**
     * 如果未加入频道，获取频道信息
     *
     * @param groupId 群聊id
     * @return 频道信息
     */
    GroupVO getGroupVoIfNotJoin(Integer groupId);

    /**
     * 根据userId和关键词获取群列表
     *
     * @param userId  用户id
     * @param keyword 关键词
     * @return 群列表
     */
    List<Group> getGroupList(Integer userId, String keyword);

    /**
     * 批量删除群聊
     *
     * @param idList 群聊id
     * @return 影响行数
     */
    Long deleteGroupList(List<Integer> idList);

    /**
     * 删除群聊
     *
     * @param groupId 群聊id
     * @return 影响行数
     */
    Long deleteGroup(Integer groupId);

    /**
     * 根据关键字查找
     *
     * @param userId     用户id
     * @param searchType 搜索类型
     * @param keyword    关键词
     * @return 查找结果列表
     */
    List<SearchVO> getGroupSearchVoList(Integer userId, String searchType, String keyword);
}
