package com.project.gimme.service.impl;

import com.project.gimme.mapper.GroupNoticeMapper;
import com.project.gimme.pojo.GroupNotice;
import com.project.gimme.service.GroupNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 9:48
 */
@Service
public class GroupNoticeServiceImpl implements GroupNoticeService {
    @Resource
    private GroupNoticeMapper groupNoticeMapper;

    /**
     * 创建群公告
     *
     * @param groupNotice 被创建的群公告
     * @return 是否成功
     */
    @Override
    public Boolean createGroupNotice(GroupNotice groupNotice) {
        return groupNoticeMapper.createGroupNotice(groupNotice);
    }

    /**
     * 更新群公告
     *
     * @param groupNotice 要更新的群公告
     * @return 影响行数
     */
    @Override
    public Long updateGroupNotice(GroupNotice groupNotice) {
        return groupNoticeMapper.updateGroupNotice(groupNotice);
    }

    /**
     * 通过groupId或者groupNotice获取群公告
     *
     * @param groupId       群id
     * @param groupNoticeId 群公告id
     * @return 群公告
     */

    @Override
    public GroupNotice getGroupNotice(Integer groupId, Integer groupNoticeId) {
        if (groupNoticeId != null) {
            return groupNoticeMapper.getGroupNotice(groupNoticeId);
        } else {
            return groupNoticeMapper.getNewGroupNotice(groupId);
        }

    }

    /**
     * 根据群id获取群公告列表
     *
     * @param groupId 群id
     * @return 群公告列表
     */
    @Override
    public List<GroupNotice> getGroupNoticeList(Integer groupId) {
        return groupNoticeMapper.getGroupNoticeList(groupId);
    }

    /**
     * 批量删除群公告
     *
     * @param groupId 群聊id
     * @param idList  id列表
     * @return 影响行数
     */
    @Override
    public Long deleteGroupNotice(Integer groupId, List<Integer> idList) {
        return groupNoticeMapper.deleteGroupNotice(groupId, idList);
    }
}
