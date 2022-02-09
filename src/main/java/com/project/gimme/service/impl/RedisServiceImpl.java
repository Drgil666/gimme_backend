package com.project.gimme.service.impl;

import com.project.gimme.dao.RedisDao;
import com.project.gimme.pojo.vo.TokenFriendVO;
import com.project.gimme.pojo.vo.TokenGroupVO;
import com.project.gimme.pojo.vo.TokenUserTimestampVO;
import com.project.gimme.pojo.vo.TokenUserVO;
import com.project.gimme.service.RedisService;
import com.project.gimme.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

import static com.project.gimme.utils.RedisUtil.*;

/**
 * @author DrGilbert
 * @date 2022/2/6 17:01
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisDao redisDao;
    @Value("${token.validity}")
    private Long timeValidity;

    /**
     * 根据用户id创建token
     *
     * @param userId 用户id
     * @return 创建好的token
     */
    @Override
    public String createUserLoginToken(Integer userId) {
        String uuid = UUID.randomUUID().toString();
        TokenUserVO tokenUserVO = new TokenUserVO();
        tokenUserVO.setType(TOKEN_USER_LOGIN);
        tokenUserVO.setUserId(userId);
        TokenUserTimestampVO tokenUserTimestampVO = new TokenUserTimestampVO();
        tokenUserTimestampVO.setTimestamp(new Date());
        tokenUserTimestampVO.setType(TOKEN_TIMESTAMP);
        String json = RedisUtil.objectToJsonString(tokenUserVO);
        String object = redisDao.getStringValue(json);
        if (!StringUtils.isEmpty(json)) {
            redisDao.deleteStringValue(json);
        }
        if (!StringUtils.isEmpty(object)) {
            redisDao.deleteStringValue(object);
        }
        redisDao.createStringValue(uuid, json);
        //创建uuid->token映射
        redisDao.createStringValue(json, uuid);
        //创建token->uuid映射
        String timestampKey = getTimeStampKey(userId);
        if (!StringUtils.isEmpty(timestampKey)) {
            redisDao.deleteStringValue(timestampKey);
        }
        redisDao.createStringValue(timestampKey,
                RedisUtil.objectToJsonString(tokenUserTimestampVO));
        return uuid;
    }

    /**
     * 根据token判断验证是否通过
     *
     * @param uuid 用户token
     * @return 是否验证成功
     */
    @Override
    public Boolean checkUserLoginToken(String uuid) {
        String json = redisDao.getStringValue(uuid);
        TokenUserVO tokenUserVO = (TokenUserVO) RedisUtil.jsonStringToObject(json, TokenUserVO.class);
        if (tokenUserVO != null && tokenUserVO.getType().equals(TOKEN_USER_LOGIN)) {
            //token类型正确
            String timestampJson = redisDao.getStringValue(
                    getTimeStampKey(tokenUserVO.getUserId()));
            if (timestampJson != null) {
                TokenUserTimestampVO tokenUserTimestampVO = (TokenUserTimestampVO) RedisUtil.jsonStringToObject(timestampJson, TokenUserTimestampVO.class);
                if (tokenUserTimestampVO.getTimestamp() != null &&
                        (System.currentTimeMillis() - tokenUserTimestampVO.getTimestamp().getTime())
                                <= timeValidity * 24 * 60 * 60 * 1000) {
                    //token未过期
                    String anotherUuid = redisDao.getStringValue(json);
                    if (uuid.equals(anotherUuid)) {
                        //双向验证成功
                        return true;
                    } else {
                        redisDao.deleteStringValue(json);
                        //清除过期token
                    }
                }
            }
        }
        return false;
    }

    /**
     * 根据token获取用户id
     *
     * @param token 用户token
     * @return 用户id
     */
    @Override
    public Integer getUserId(String token) {
        String json = redisDao.getStringValue(token);
        TokenUserVO tokenUserVO = (TokenUserVO) RedisUtil.jsonStringToObject(json, TokenUserVO.class);
        return tokenUserVO.getUserId();
    }

    /**
     * 根据二人id创建好友关系token
     *
     * @param userId   用户id
     * @param friendId 朋友
     */
    @Override
    public void createFriendToken(Integer userId, Integer friendId) {
        TokenFriendVO tokenFriendVO = new TokenFriendVO();
        tokenFriendVO.setType(TOKEN_FRIEND);
        tokenFriendVO.setTimestamp(new Date());
        String key1 = getFriendKey(userId, friendId);
        String json1 = RedisUtil.objectToJsonString(tokenFriendVO);
        redisDao.createStringValue(key1, json1);
        String key2 = getFriendKey(friendId, userId);
        redisDao.createStringValue(key2, json1);
    }

    /**
     * 验证是否存在好友关系
     *
     * @param userId   用户id
     * @param friendId 朋友id
     * @return 是否是好友关系
     */
    @Override
    public Boolean checkFriendToken(Integer userId, Integer friendId) {
        String key1 = getFriendKey(userId, friendId);
        String key2 = getFriendKey(friendId, userId);
        if (!StringUtils.isEmpty(redisDao.getStringValue(key1))
                && !StringUtils.isEmpty(redisDao.getStringValue(key2))) {
            //双向验证
            return true;
        } else {
            redisDao.deleteStringValue(key1);
            redisDao.deleteStringValue(key2);
            //清理无用token
            return false;
        }
    }

    /**
     * 删除好友关系
     *
     * @param userId   用户id
     * @param friendId 好友id
     */
    @Override
    public void deleteUserLoginToken(Integer userId, Integer friendId) {
        redisDao.deleteStringValue(getFriendKey(userId, friendId));
    }

    /**
     * 为群聊创建人员权利token
     *
     * @param userId   用户id
     * @param groupId  群聊id
     * @param typeName 类型名称
     */
    @Override
    public void createGroupAuthorityToken(Integer userId, Integer groupId, String typeName) {
        TokenGroupVO tokenGroupVO = new TokenGroupVO();
        tokenGroupVO.setType(TOKEN_GROUP);
        tokenGroupVO.setGroupId(groupId);
        tokenGroupVO.setUserId(userId);
        tokenGroupVO.setMemberType(typeName);
        String key = getGroupUserKey(groupId, userId);
        String json = RedisUtil.objectToJsonString(tokenGroupVO);
        redisDao.deleteStringValue(key);
        redisDao.createStringValue(key, json);
    }

    /**
     * 通过用户id与群聊id获取权限token
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return token权限
     */
    @Override
    public String getGroupAuthorityToken(Integer userId, Integer groupId) {
        String json = redisDao.getStringValue(getGroupUserKey(groupId, userId));
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        TokenGroupVO tokenGroupVO = (TokenGroupVO) RedisUtil.jsonStringToObject(json, TokenGroupVO.class);
        if (tokenGroupVO == null) {
            return null;
        }
        return tokenGroupVO.getMemberType();
    }

    /**
     * 用户移出群聊
     *
     * @param userId  用户id
     * @param groupId 群聊id
     */
    @Override
    public void deleteGroupAuthorityToken(Integer userId, Integer groupId) {
        redisDao.deleteStringValue(getGroupUserKey(groupId, userId));
    }

    /**
     * 解散群
     *
     * @param groupId 群聊id
     */
    @Override
    public void deleteGroupToken(Integer groupId) {
        redisDao.deleteKeys(getGroupKey(groupId));
    }

    /**
     * 为群聊创建人员权利token
     *
     * @param userId    用户id
     * @param channelId 群聊id
     * @param typeName  类型名称
     */
    @Override
    public void createChannelAuthorityToken(Integer userId, Integer channelId, String typeName) {
        TokenGroupVO tokenGroupVO = new TokenGroupVO();
        tokenGroupVO.setType(TOKEN_CHANNEL);
        tokenGroupVO.setGroupId(channelId);
        tokenGroupVO.setUserId(userId);
        tokenGroupVO.setMemberType(typeName);
        String key = getChannelUserKey(channelId, userId);
        String json = RedisUtil.objectToJsonString(tokenGroupVO);
        redisDao.deleteStringValue(key);
        redisDao.createStringValue(key, json);
    }

    /**
     * 通过用户id与群聊id获取权限token
     *
     * @param userId    用户id
     * @param channelId 群聊id
     * @return token权限
     */
    @Override
    public String getChannelAuthorityToken(Integer userId, Integer channelId) {
        String json = redisDao.getStringValue(getChannelUserKey(channelId, userId));
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        TokenGroupVO tokenGroupVO = (TokenGroupVO) RedisUtil.jsonStringToObject(json, TokenGroupVO.class);
        if (tokenGroupVO == null) {
            return null;
        }
        return tokenGroupVO.getMemberType();
    }

    /**
     * 用户移出群聊
     *
     * @param userId    用户id
     * @param channelId 群聊id
     */
    @Override
    public void deleteChannelAuthorityToken(Integer userId, Integer channelId) {
        redisDao.deleteStringValue(getChannelUserKey(channelId, userId));
    }

    /**
     * 解散群
     *
     * @param channelId 群聊id
     */
    @Override
    public void deleteChannelToken(Integer channelId) {
        redisDao.deleteKeys(getChannelKey(channelId));
    }

    /**
     * 检查该群是否已存在
     *
     * @param groupId 群id
     * @return 是否存在
     */
    @Override
    public Boolean checkGroupExist(Integer groupId) {
        return redisDao.checkIfExist(getGroupKey(groupId));
    }

    /**
     * 检查该频道是否已存在
     *
     * @param channelId 频道id
     * @return 是否存在
     */
    @Override
    public Boolean checkChannelExist(Integer channelId) {
        return redisDao.checkIfExist(getChannelKey(channelId));
    }

    private String getFriendKey(Integer userId, Integer friendId) {
        return TOKEN_FRIEND + "_" + userId + "_" + friendId;
    }

    private String getTimeStampKey(Integer userId) {
        return TOKEN_TIMESTAMP + "_" + userId;
    }

    private String getGroupUserKey(Integer groupId, Integer userId) {
        return TOKEN_GROUP + "_" + groupId + "_" + userId;
    }

    private String getGroupKey(Integer groupId) {
        return TOKEN_GROUP + "_" + groupId + "_";
    }

    private String getChannelUserKey(Integer channelId, Integer userId) {
        return TOKEN_CHANNEL + "_" + channelId + "_" + userId;
    }

    private String getChannelKey(Integer channelId) {
        return TOKEN_CHANNEL + "_" + channelId + "_";
    }
}
