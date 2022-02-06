package com.project.gimme.service.impl;

import com.project.gimme.dao.RedisDao;
import com.project.gimme.pojo.vo.TokenItem;
import com.project.gimme.pojo.vo.TokenVO;
import com.project.gimme.service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    /**
     * 根据用户id创建token
     *
     * @param userId 用户id
     * @return 创建好的token
     */
    @Override
    public String createUserLoginToken(Integer userId) {
        String uuid = UUID.randomUUID().toString();
        List<TokenItem> tokenItemList = new ArrayList<>();
        TokenItem tokenItem = new TokenItem();
        tokenItem.setKey(TOKEN_PARAM_UUID);
        tokenItem.setValue(uuid);
        tokenItemList.add(tokenItem);
        tokenItem.setKey(TOKEN_PARAM_TIMESTAMP);
        tokenItem.setValue(new Date().toString());
        tokenItemList.add(tokenItem);
        TokenVO tokenVO = new TokenVO();
        tokenVO.setTokenType(TOKEN_USER_LOGIN);
        tokenVO.setItemList(tokenItemList);
        String json = objectToJson(tokenVO);
        //TODO:待完成
        return null;
    }

    /**
     * 根据token判断验证是否通过
     *
     * @param token 用户token
     * @return 是否验证成功
     */
    @Override
    public Boolean checkUserLoginToken(String token) {
        return null;
    }

    /**
     * 根据二人id创建好友关系token
     *
     * @param userId   用户id
     * @param friendId 朋友
     */
    @Override
    public void createFriendToken(Integer userId, Integer friendId) {

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
        return null;
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
        return null;
    }
}
