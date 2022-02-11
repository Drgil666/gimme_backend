package com.project.gimme.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * @author DrGilbert
 * @date 2022/1/14 16:04
 */
public class PersonalMsgUtil {
    public static final String INSERT_FRIEND_ATTRIBUTE = "insert_friend";
    public static final String DELETE_FRIEND_ATTRIBUTE = "delete_friend";
    public static final String INSERT_GROUP_ATTRIBUTE = "insert_group";
    public static final String DELETE_GROUP_ATTRIBUTE = "delete_group";
    public static final String INSERT_GROUP_MEMBER_ATTRIBUTE = "insert_group_member";
    public static final String UPDATE_GROUP_MEMBER_ATTRIBUTE = "update_group_member";
    public static final String DELETE_GROUP_MEMBER_ATTRIBUTE = "channel";
    public static final String INSERT_CHANNEL_ATTRIBUTE = "insert_channel";
    public static final String UPDATE_CHANNEL_ATTRIBUTE = "update_channel";
    public static final String DELETE_CHANNEL_ATTRIBUTE = "delete_channel";
    public static final String INSERT_CHANNEL_MEMBER_ATTRIBUTE = "insert_channel_member";
    public static final String UPDATE_CHANNEL_MEMBER_ATTRIBUTE = "update_channel_member";
    public static final String DELETE_CHANNEL_MEMBER_ATTRIBUTE = "delete_channel_member";
    public static final String ACCEPT_ATTRIBUTE = "已同意";
    public static final String REJECT_ATTRIBUTE = "已拒绝";
    public static final String NULL_ATTRIBUTE = "null";

    @AllArgsConstructor
    @Getter
    public enum MsgType {
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

    @AllArgsConstructor
    @Getter
    public enum Status {
        /**
         * 请求已同意
         */
        TYPE_ACCEPT(0, ACCEPT_ATTRIBUTE),
        /**
         * 请求已拒绝
         */
        TYPE_REJECT(1, REJECT_ATTRIBUTE),
        /**
         * 空信息
         */
        TYPE_NULL(2, NULL_ATTRIBUTE);
        private final Integer code;
        private final String name;
    }

    public static final MsgType[] MSG_TYPE_LIST = MsgType.values();
    public static final HashMap<String, Integer> CHAT_MSG_MAP = getMsgTypeMap();
    public static final Status[] STATUS_LIST = Status.values();
    public static final HashMap<String, Integer> STATUS_MAP = getStatusMap();

    public static HashMap<String, Integer> getStatusMap() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        for (Status status : STATUS_LIST) {
            hashMap.put(status.name, status.code);
        }
        return hashMap;
    }

    public static HashMap<String, Integer> getMsgTypeMap() {
        HashMap<String, Integer> hashMap = new HashMap<>(10);
        for (MsgType msgType : MSG_TYPE_LIST) {
            hashMap.put(msgType.name, msgType.code);
        }
        return hashMap;
    }

    public static Integer getTypeMsgByName(String name) {
        if (CHAT_MSG_MAP.containsKey(name)) {
            return CHAT_MSG_MAP.get(name);
        }
        return null;
    }

    public static Integer getStatusByName(String name) {
        if (STATUS_MAP.containsKey(name)) {
            return STATUS_MAP.get(name);
        }
        return null;
    }
}
