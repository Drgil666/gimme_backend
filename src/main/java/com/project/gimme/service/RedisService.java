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
}
