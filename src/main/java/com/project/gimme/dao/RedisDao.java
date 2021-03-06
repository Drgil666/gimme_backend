package com.project.gimme.dao;

import java.util.HashMap;

/**
 * @author Gilbert
 * @date 2020/11/30 16:05
 */
public interface RedisDao {
    /**
     * 为redis设置字符串键值对
     *
     * @param key   键
     * @param value 值
     */
    void createStringValue(String key, String value);

    /**
     * 获取键对应的字符串值
     *
     * @param key 键
     * @return 对应值
     */
    String getStringValue(String key);

    /**
     * 删除键对应的字符串值
     *
     * @param key 键
     */
    void deleteStringValue(String key);

    /**
     * 为redis设置hash键值对
     *
     * @param name 名称
     * @param map  键值对集合
     */
    void createHashValue(String name, HashMap<String, String> map);

    /**
     * 获取键对应的hash值
     *
     * @param name 名称
     * @param key  键
     * @return 对应值
     */
    String getHashValue(String name, String key);

    /**
     * 删除键对应的hash值
     *
     * @param key  键
     * @param name 名称
     */
    void deleteHashValue(String name, String key);

    /**
     * 批量寻找关键词并删除
     *
     * @param key 关键词
     */
    void deleteKeys(String key);

    /**
     * 确认是否存在
     *
     * @param key 关键词
     * @return 是否存在
     */
    Boolean checkIfExist(String key);
}
