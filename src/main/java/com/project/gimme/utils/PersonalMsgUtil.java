package com.project.gimme.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * @author DrGilbert
 * @date 2022/1/14 16:04
 */
public class PersonalMsgUtil {
    private static final String INSERT_FRIEND_ATTRIBUTE = "insert_friend";
    private static final String DELETE_FRIEND_ATTRIBUTE = "delete_friend";
    private static final String INSERT_GROUP_ATTRIBUTE = "insert_group";
    private static final String DELETE_GROUP_ATTRIBUTE = "delete_group";
    private static final String INSERT_GROUP_MEMBER_ATTRIBUTE = "insert_group_member";
    private static final String UPDATE_GROUP_MEMBER_ATTRIBUTE = "update_group_member";
    private static final String DELETE_GROUP_MEMBER_ATTRIBUTE = "channel";
    private static final String INSERT_CHANNEL_ATTRIBUTE = "insert_channel";
    private static final String UPDATE_CHANNEL_ATTRIBUTE = "update_channel";
    private static final String DELETE_CHANNEL_ATTRIBUTE = "delete_channel";
    private static final String INSERT_CHANNEL_MEMBER_ATTRIBUTE = "insert_channel_member";
    private static final String UPDATE_CHANNEL_MEMBER_ATTRIBUTE = "update_channel_member";
    private static final String DELETE_CHANNEL_MEMBER_ATTRIBUTE = "delete_channel_member";

    @AllArgsConstructor
    @Getter
    public enum Character {
        /**
         * 添加好友请求
         */
        TYPE_INSERT_FRIEND(0, INSERT_FRIEND_ATTRIBUTE),
        /**
         * 删除好友通知
         */
        TYPE_DELETE_FRIEND(1, DELETE_FRIEND_ATTRIBUTE),
        /**
         * 创建频道通知
         */
        TYPE_INSERT_GROUP(2, INSERT_GROUP_ATTRIBUTE),
        /**
         * 删除频道通知
         */
        TYPE_DELETE_GROUP(3, DELETE_GROUP_ATTRIBUTE),
        /**
         * 添加群成员请求
         */
        TYPE_INSERT_GROUP_MEMBER(4, INSERT_GROUP_MEMBER_ATTRIBUTE),
        /**
         * 更新群成员通知
         */
        TYPE_UPDATE_GROUP_MEMBER(5, UPDATE_GROUP_MEMBER_ATTRIBUTE),
        /**
         * 删除群成员通知
         */
        TYPE_DELETE_GROUP_MEMBER(6, DELETE_GROUP_MEMBER_ATTRIBUTE),
        /**
         * 创建频道通知
         */
        TYPE_INSERT_CHANNEL(7, INSERT_CHANNEL_ATTRIBUTE),
        /**
         * 更新频道通知
         */
        TYPE_UPDATE_CHANNEL(8, UPDATE_CHANNEL_ATTRIBUTE),
        /**
         * 删除频道通知
         */
        TYPE_DELETE_CHANNEL(9, DELETE_CHANNEL_ATTRIBUTE),
        /**
         * 添加频道成员请求
         */
        TYPE_INSERT_CHANNEL_MEMBER(10, INSERT_CHANNEL_MEMBER_ATTRIBUTE),
        /**
         * 添加频道成员请求
         */
        TYPE_UPDATE_CHANNEL_MEMBER(11, UPDATE_CHANNEL_MEMBER_ATTRIBUTE),
        /**
         * 添加频道成员请求
         */
        TYPE_DELETE_CHANNEL_MEMBER(12, DELETE_CHANNEL_MEMBER_ATTRIBUTE);
        private final Integer code;
        private final String name;
    }

    public static final Character[] CHARACTER_LIST = Character.values();
    public static final HashMap<String, Integer> CHARACTER_MAP = getCharacterMap();

    public static HashMap<String, Integer> getCharacterMap() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        for (Character character : CHARACTER_LIST) {
            hashMap.put(character.name, character.code);
        }
        return hashMap;
    }

    public static Integer getCharacterByName(String name) {
        if (CHARACTER_MAP.containsKey(name)) {
            return CHARACTER_MAP.get(name);
        }
        return null;
    }
}
