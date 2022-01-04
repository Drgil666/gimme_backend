package com.project.gimme.service.impl;

import com.project.gimme.mapper.GroupFileMapper;
import com.project.gimme.pojo.GroupFile;
import com.project.gimme.service.GroupFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 17:04
 */
@Service
public class GroupFileServiceImpl implements GroupFileService {
    @Resource
    private GroupFileMapper groupFileMapper;

    /**
     * 创建群文件
     *
     * @param groupFile 被创建的群文件
     * @return 是否成功
     */
    @Override
    public Boolean createGroupFile(GroupFile groupFile) {
        return groupFileMapper.createGroupFile(groupFile);
    }

    /**
     * 更新群文件
     *
     * @param groupFile 要更新的群文件
     * @return 影响行数
     */
    @Override
    public Long updateGroupFile(GroupFile groupFile) {
        return groupFileMapper.updateGroupFile(groupFile);
    }

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public GroupFile getGroupFile(Integer id) {
        return groupFileMapper.getGroupFile(id);
    }

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param groupId 群聊id
     * @param keyword 文件名
     * @return 查询的用户列表
     */
    @Override
    public List<GroupFile> getGroupByGroupId(Integer groupId, String keyword) {
        return groupFileMapper.getGroupByGroupId(groupId, keyword);
    }
}
