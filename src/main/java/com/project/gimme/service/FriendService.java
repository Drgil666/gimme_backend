package com.project.gimme.service;

import com.project.gimme.pojo.Friend;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/12 14:23
 */
public interface FriendService {
    /**
     * 创建朋友关系
     *
     * @param friend 要创建的朋友
     * @return 是否成功
     */
    Boolean createFriend(Friend friend);

    /**
     * 更新朋友关系
     *
     * @param friend 要更新的朋友
     * @return 影响行数
     */
    Long updateFriend(Friend friend);

    /**
     * 获取朋友关系
     *
     * @param userId   用户id
     * @param friendId 朋友id
     * @return 朋友关系
     */
    Friend getFriend(Integer userId, Integer friendId);

    /**
     * 批量删除好友关系
     *
     * @param userId 用户id
     * @param idList 好友id列表
     * @return 影响行数
     */
    Long deleteFriend(Integer userId, List<Integer> idList);
}