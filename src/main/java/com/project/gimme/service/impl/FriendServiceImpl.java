package com.project.gimme.service.impl;

import com.project.gimme.mapper.FriendMapper;
import com.project.gimme.pojo.Friend;
import com.project.gimme.pojo.vo.SearchVO;
import com.project.gimme.service.FriendService;
import com.project.gimme.utils.ContactsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/12 14:23
 */
@Service
public class FriendServiceImpl implements FriendService {
    @Resource
    private FriendMapper friendMapper;

    /**
     * 创建朋友关系
     *
     * @param friend 要创建的朋友
     * @return 是否成功
     */
    @Override
    public Boolean createFriend(Friend friend) {
        friend.setMsgTimestamp(new Date());
        return friendMapper.createFriend(friend);
    }

    /**
     * 更新朋友关系
     *
     * @param friend 要更新的朋友
     * @return 影响行数
     */
    @Override
    public Long updateFriend(Friend friend) {
        return friendMapper.updateFriend(friend);
    }

    /**
     * 获取朋友关系
     *
     * @param userId   用户id
     * @param friendId 朋友id
     * @return 朋友关系
     */
    @Override
    public Friend getFriend(Integer userId, Integer friendId) {
        return friendMapper.getFriend(userId, friendId);
    }

    /**
     * 批量删除好友关系
     *
     * @param userId 用户id
     * @param idList 好友id列表
     * @return 影响行数
     */
    @Override
    public Long deleteFriend(Integer userId, List<Integer> idList) {
        return friendMapper.deleteFriend(userId, idList);
    }

    /**
     * 通过用户id获取朋友列表
     *
     * @param userId  用户id
     * @param keyword 关键词
     * @return 朋友列表
     */
    @Override
    public List<Friend> getFriendList(Integer userId, String keyword) {
        return friendMapper.getFriendList(userId, keyword);
    }

    /**
     * 根据关键字查找
     *
     * @param userId     用户id
     * @param searchType 搜索类型
     * @param keyword    关键词
     * @return 查找结果列表
     */
    @Override
    public List<SearchVO> getFriendSearchVoList(Integer userId, String searchType, String keyword) {
        List<SearchVO> searchVOList = friendMapper.getFriendSearchVoList(userId, searchType, keyword);
        for (SearchVO searchVO : searchVOList) {
            if (searchType.equals(ContactsUtil.SearchType.TYPE_CONTACTS.getName())) {
                searchVO.setIsJoined(true);
            } else {
                searchVO.setIsJoined(getFriend(userId, searchVO.getObjectId()) != null);
            }
        }
        return searchVOList;
    }
}
