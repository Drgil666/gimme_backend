package com.project.gimme.service;

import com.project.gimme.pojo.GroupFile;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 17:03
 */
public interface GroupFileService {
    /**
     * 创建群文件
     *
     * @param groupFile 被创建的群文件
     * @return 是否成功
     */
    Boolean createGroupFile(GroupFile groupFile);

    /**
     * 更新群文件
     *
     * @param groupFile 要更新的群文件
     * @return 影响行数
     */
    Long updateGroupFile(GroupFile groupFile);

    /**
     * 通过id获取用户信息
     *
     * @param id 用户id
     * @return 用户
     */
    GroupFile getGroupFile(Integer id);

    /**
     * 根据群聊id和文件名查询群文件列表
     *
     * @param groupId 群聊id
     * @param keyword 文件名
     * @return 查询的用户列表
     */
    List<GroupFile> getGroupByGroupId(Integer groupId, String keyword);

}
