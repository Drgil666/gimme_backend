package com.project.gimme.mapper;

import com.project.gimme.pojo.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("insert into friend (user_id, friend_id, friend_note) VALUES " +
            "(#{friend.userId},#{friend.friendId},#{friend.friendNote})")
    Boolean createFriend(@Param("friend") Friend friend);

    /**
     * 更新朋友关系
     *
     * @param friend 要更新的朋友
     * @return 影响行数
     */
    @Update("update friend set friend_note=#{friend.friendNote} where " +
            "user_id=#{friend.userId} and friend_id=#{friend.friendId}")
    Long updateFriend(@Param("friend") Friend friend);

    /**
     * 获取朋友关系
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
     * 批量删除好友关系
     *
     * @param userId 用户id
     * @param idList 好友id列表
     * @return 影响行数
     */
    Long deleteFriend(@Param("userId") Integer userId,
                      @Param("idList") List<Integer> idList);
}
