package com.project.gimme.service.impl;

import com.project.gimme.exception.ErrorCode;
import com.project.gimme.exception.ErrorException;
import com.project.gimme.mapper.ChannelUserMapper;
import com.project.gimme.pojo.ChannelUser;
import com.project.gimme.service.ChannelUserService;
import com.project.gimme.service.RedisService;
import com.project.gimme.utils.AssertionUtil;
import com.project.gimme.utils.UserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:37
 */
@Service
public class ChannelUserServiceImpl implements ChannelUserService {
    @Resource
    private ChannelUserMapper channelUserMapper;
    @Resource
    private RedisService redisService;

    /**
     * 创建频道成员
     *
     * @param channelUser 被创建的频道成员类
     * @return 是否成功
     */
    @Override
    public Boolean createChannelUser(ChannelUser channelUser) {
        return channelUserMapper.createChannelUser(channelUser);
    }

    /**
     * 更新频道成员
     *
     * @param channelUser 要更新的频道成员
     * @return 影响行数
     */
    @Override
    public Long updateChannelUser(ChannelUser channelUser) {
        return channelUserMapper.updateChannelUser(channelUser);
    }

    /**
     * 通过id获取频道成员
     *
     * @param channelId 频道id
     * @param userId    用户id
     * @return 频道成员
     */
    @Override
    public ChannelUser getChannelUser(Integer channelId, Integer userId) {
        return channelUserMapper.getChannelUser(channelId, userId);
    }

    /**
     * 批量删除频道成员
     *
     * @param channelId 频道id
     * @param idList    id列表
     * @return 影响行数
     */
    @Override
    public Long deleteChannelUser(Integer channelId, List<Integer> idList) {
        return channelUserMapper.deleteChannelUser(channelId, idList);
    }

    /**
     * 判断是否有权限
     *
     * @param userId    用户id
     * @param channelId 群聊id
     * @param typeName  类型id
     * @return 是否有权限
     */
    @Override
    public void authorityCheck(Integer userId, Integer channelId, String typeName) {
        String userTypeName = redisService.getChannelAuthorityToken(userId, channelId);
        AssertionUtil.notNull(userTypeName, ErrorCode.AUTHORIZE_ILLEGAL, ErrorCode.AUTHORIZE_ILLEGAL.getMsg());
        Integer userValue = UserUtil.getChannelCharacterByName(userTypeName);
        Integer value = UserUtil.getChannelCharacterByName(typeName);
        AssertionUtil.notNull(userValue, ErrorCode.AUTHORIZE_ILLEGAL, ErrorCode.AUTHORIZE_ILLEGAL.getMsg());
        AssertionUtil.notNull(value, ErrorCode.AUTHORIZE_ILLEGAL, ErrorCode.AUTHORIZE_ILLEGAL.getMsg());
        if (userValue > value) {
            throw new ErrorException(ErrorCode.AUTHORIZE_ILLEGAL);
        }
    }
}
