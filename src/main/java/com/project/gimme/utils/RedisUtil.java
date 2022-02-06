package com.project.gimme.utils;

import com.alibaba.fastjson.JSONObject;
import com.project.gimme.pojo.vo.TokenVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;

/**
 * @author DrGilbert
 * @date 2022/2/6 16:54
 */
public class RedisUtil {
    public static final String TOKEN_USER_LOGIN = "token_user_login";
    public static final String TOKEN_FRIEND = "token_friend";
    public static final String TOKEN_GROUP = "token_group";
    public static final String TOKEN_PARAM_UUID = "uuid";
    public static final String TOKEN_PARAM_TIMESTAMP = "timestamp";

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
        TYPE_GROUP(2, TOKEN_GROUP);
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

    public static String objectToJson(TokenVO tokenVO) {
        return JSONObject.toJSONString(tokenVO);
    }

    public static T jsonToObject(String json, T object) {
        return JSONObject.parseObject(json, T.class);
    }
    //TODO:待测试
}
