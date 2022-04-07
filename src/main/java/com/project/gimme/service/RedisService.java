package com.project.gimme.service;

/**
 * @author DrGilbert
 * @date 2022/2/6 16:45
 */
public interface RedisService {
    /**
     * 根据用户id创建token
     *
     * @param userId 用户id
     * @return 创建好的token
     */
    String createUserLoginToken(Integer userId);

    /**
     * 根据token判断验证是否通过
     *
     * @param token 用户token
     * @return 是否验证成功
     */
    Boolean checkUserLoginToken(String token);

    /**
     * 根据token获取用户id
     *
     * @param token 用户token
     * @return 用户id
     */
    Integer getUserId(String token);

    /**
     * 根据二人id创建好友关系token
     *
     * @param userId   用户id
     * @param friendId 朋友
     */
    void createFriendToken(Integer userId, Integer friendId);

    /**
     * 验证是否存在好友关系
     *
     * @param userId   用户id
     * @param friendId 朋友id
     * @return 是否是好友关系
     */
    Boolean checkFriendToken(Integer userId, Integer friendId);

    /**
     * 删除好友关系
     *
     * @param userId   用户id
     * @param friendId 好友id
     */
    void deleteFriendToken(Integer userId, Integer friendId);

    /**
     * 为群聊创建人员权利token
     *
     * @param userId   用户id
     * @param groupId  群聊id
     * @param typeName 类型名称
     */
    void createGroupAuthorityToken(Integer userId, Integer groupId, String typeName);

    /**
     * 通过用户id与群聊id获取权限token
     *
     * @param userId  用户id
     * @param groupId 群聊id
     * @return token权限
     */
    String getGroupAuthorityToken(Integer userId, Integer groupId);

    /**
     * 用户移出群聊
     *
     * @param userId  用户id
     * @param groupId 群聊id
     */
    void deleteGroupAuthorityToken(Integer userId, Integer groupId);

    /**
     * 解散群
     *
     * @param groupId 群聊id
     */
    void deleteGroupToken(Integer groupId);

    /**
     * 为群聊创建人员权利token
     *
     * @param userId    用户id
     * @param channelId 群聊id
     * @param typeName  类型名称
     */
    void createChannelAuthorityToken(Integer userId, Integer channelId, String typeName);

    /**
     * 通过用户id与群聊id获取权限token
     *
     * @param userId    用户id
     * @param channelId 群聊id
     * @return token权限
     */
    String getChannelAuthorityToken(Integer userId, Integer channelId);

    /**
     * 用户移出群聊
     *
     * @param userId    用户id
     * @param channelId 群聊id
     */
    void deleteChannelAuthorityToken(Integer userId, Integer channelId);

    /**
     * 解散群
     *
     * @param channelId 群聊id
     */
    void deleteChannelToken(Integer channelId);

    /**
     * 检查该群是否已存在
     *
     * @param groupId 群id
     * @return 是否存在
     */
    Boolean checkGroupExist(Integer groupId);

    /**
     * 检查该频道是否已存在
     *
     * @param channelId 频道id
     * @return 是否存在
     */
    Boolean checkChannelExist(Integer channelId);

}
