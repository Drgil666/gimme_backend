package com.project.gimme.utils;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

/**
 * @author DrGilbert
 * @date 2022/2/6 16:54
 */
public class RedisUtil {
    public static final String TOKEN_USER_LOGIN = "token_user_login";
    public static final String TOKEN_FRIEND = "token_friend";
    public static final String TOKEN_GROUP = "token_group";
    public static final String TOKEN_CHANNEL = "token_channel";
    public static final String TOKEN_UUID = "uuid";
    public static final String TOKEN_TIMESTAMP = "timestamp";
    public static final String TOKEN_TYPE = "token_type";

    @AllArgsConstructor
    @Getter
    public enum Character {
        /**
         * 用户登录token
         */
        TYPE_USER_LOGIN(0, TOKEN_USER_LOGIN),
        /**
         * 好友关系token
         */
        TYPE_FRIEND(1, TOKEN_FRIEND),
        /**
         * 群聊人员权限token
         */
        TYPE_GROUP(2, TOKEN_GROUP),
        /**
         * 时间戳token
         */
        TYPE_TIMESTAMP(3, TOKEN_TIMESTAMP),
        /**
         *
         */
        TYPE_CHANNEL(4, TOKEN_CHANNEL);
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

    public static String objectToJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static Object jsonStringToObject(String json, Class cls) {
        return JSON.parseObject(json, cls);
    }
}
