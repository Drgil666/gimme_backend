package com.project.gimme.mapper;

import com.project.gimme.pojo.Friend;
import com.project.gimme.pojo.vo.SearchVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/12 14:15
 */
@Mapper
public interface FriendMapper {
    /**
     * 创建朋友关系
     *
     * @param friend 要创建的朋友
     * @return 是否成功
     */
    @Insert("insert into friend (user_id, friend_id, friend_note,msg_timestamp) VALUES " +
            "(#{friend.userId},#{friend.friendId},#{friend.friendNote},#{friend.msgTimestamp})")
    Boolean createFriend(@Param("friend") Friend friend);

    /**
     * 更新朋友关系
     *
     * @param friend 要更新的朋友
     * @return 影响行数
     */
    @Update("update friend set friend_note=#{friend.friendNote}," +
            "msg_timestamp=#{friend.msgTimestamp} " +
            "where user_id=#{friend.userId} and friend_id=#{friend.friendId}")
    Long updateFriend(@Param("friend") Friend friend);

    /**
     * 通过好友id与用户id获取朋友关系
     *
     * @param userId   用户id
     * @param friendId 朋友id
     * @return 朋友关系
     */
    @Select("select * from friend where user_id=#{userId} " +
            "and friend_id=#{friendId}")
    Friend getFriend(@Param("userId") Integer userId,
                     @Param("friendId") Integer friendId);

    /**
     * 通过用户id获取朋友列表
     *
     * @param userId 用户id
     * @return 朋友列表
     */
    @Select("select * from friend where user_id=#{userId}")
    List<Friend> getFriendList(@Param("userId") Integer userId);

    /**
     * 批量删除好友关系
     *
     * @param userId 用户id
     * @param idList 好友id列表
     * @return 影响行数
     */
    Long deleteFriend(@Param("userId") Integer userId,
                      @Param("idList") List<Integer> idList);

    /**
     * 根据关键字查找
     *
     * @param userId  用户id
     * @param keyword 关键词
     * @return 查找结果列表
     */
    List<SearchVO> getFriendSearchVoList(@Param("userId") Integer userId,
                                         @Param("keyword") String keyword);
}
