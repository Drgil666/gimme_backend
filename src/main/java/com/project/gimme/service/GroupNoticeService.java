package com.project.gimme.service;

import com.project.gimme.pojo.GroupNotice;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 9:47
 */
public interface GroupNoticeService {
    /**
     * 创建群公告
     *
     * @param groupNotice 被创建的群公告
     * @return 是否成功
     */
    Boolean createGroupNotice(GroupNotice groupNotice);

    /**
     * 更新群公告
     *
     * @param groupNotice 要更新的群公告
     * @return 影响行数
     */
    Long updateGroupNotice(GroupNotice groupNotice);

    /**
     * 通过groupId或者groupNotice获取群公告
     *
     * @param groupId       群id
     * @param groupNoticeId 群公告id
     * @return 群公告
     */

    GroupNotice getGroupNotice(Integer groupId, Integer groupNoticeId);

    /**
     * 根据群id获取群公告列表
     *
     * @param groupId 群id
     * @return 群公告列表
     */
    List<GroupNotice> getGroupNoticeList(Integer groupId);

    /**
     * 批量删除群公告
     *
     * @param groupId 群聊id
     * @param idList  id列表
     * @return 影响行数
     */
    Long deleteGroupNotice(Integer groupId, List<Integer> idList);
}
