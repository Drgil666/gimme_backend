package com.project.gimme.service.impl;

import com.project.gimme.exception.ErrorCode;
import com.project.gimme.exception.ErrorException;
import com.project.gimme.mapper.GroupUserMapper;
import com.project.gimme.pojo.GroupUser;
import com.project.gimme.service.GroupUserService;
import com.project.gimme.service.RedisService;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/3 11:15
 */
@Service
public class GroupUserServiceImpl implements GroupUserService {
    @Resource
    private GroupUserMapper groupUserMapper;
    @Resource
    private RedisService redisService;

    /**
     * 创建群聊成员
     *
     * @param groupUser 被创建的群聊成员关系
     * @return 是否成功
     */
    @Override
    public Boolean createGroupUser(GroupUser groupUser) {
        groupUser.setMsgTimestamp(new Date());
        return groupUserMapper.createGroupUser(groupUser);
    }

    /**
     * 更新群聊成员权限
     *
     * @param groupUser 要更新的groupUser
     * @return 影响行数
     */
    @Override
    public Long updateGroupUser(GroupUser groupUser) {
        return groupUserMapper.updateGroupUser(groupUser);
    }

    /**
     * 通过id获取群聊成员
     *
     * @param groupId 群聊id
     * @param userId  用户id
     * @return 用户
     */
    @Override
    public GroupUser getGroupUserByUserId(Integer groupId, Integer userId) {
        return groupUserMapper.getGroupUserByUserId(groupId, userId);
    }

    /**
     * 通过用户类型获取群聊成员
     *
     * @param groupId 群聊id
     * @param type    用户类型
     * @return 用户
     */
    @Override
    public List<GroupUser> getGroupUserByType(Integer groupId, String type) {
        return groupUserMapper.getGroupUserByType(groupId, type);
    }

    /**
     * 批量删除群聊成员
     *
     * @param groupId    群聊id
     * @param userIdList 用户id列表
     * @return 影响行数
     */
    @Override
    public Long deleteGroupUser(Integer groupId, List<Integer> userIdList) {
        return groupUserMapper.deleteGroupUser(groupId, userIdList);
    }

    /**
     * 判断是否有权限
     *
     * @param userId   用户id
     * @param groupId  群聊id
     * @param typeName 类型id
     * @return 是否有权限
     */
    @Override
    public void authorityCheck(Integer userId, Integer groupId, String typeName) {
        String userTypeName = redisService.getGroupAuthorityToken(userId, groupId);
        AssertionUtil.notNull(userTypeName, ErrorCode.AUTHORIZE_ILLEGAL, ErrorCode.AUTHORIZE_ILLEGAL.getMsg());
        Integer userValue = UserUtil.getGroupCharacterByName(userTypeName);
        Integer value = UserUtil.getGroupCharacterByName(typeName);
        AssertionUtil.notNull(userValue, ErrorCode.AUTHORIZE_ILLEGAL, ErrorCode.AUTHORIZE_ILLEGAL.getMsg());
        AssertionUtil.notNull(value, ErrorCode.AUTHORIZE_ILLEGAL, ErrorCode.AUTHORIZE_ILLEGAL.getMsg());
        if (userValue > value) {
            throw new ErrorException(ErrorCode.AUTHORIZE_ILLEGAL);
        }
    }

    /**
     * 获取人员列表
     *
     * @param groupId 群聊id
     * @param type    权限类型
     * @return
     */
    @Override
    public List<Integer> getGroupAdminList(Integer groupId, Integer type) {
        return groupUserMapper.getGroupAdminList(groupId, type);
    }
}
