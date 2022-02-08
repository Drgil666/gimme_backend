package com.project.gimme.dao.impl;

import com.project.gimme.dao.RedisDao;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Gilbert
 * @date 2020/11/30 16:07
 */
@Component("RedisDaoImpl")
public class RedisDaoImpl implements RedisDao {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 为redis设置键值对
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void createStringValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取键对应的值
     *
     * @param key 键
     * @return 对应值
     */
    @Override
    public String getStringValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除键对应的字符串值
     *
     * @param key 键
     */
    @Override
    public void deleteStringValue(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 为redis设置hash键值对
     *
     * @param name 名称
     * @param map  键值对集合
     */
    @Override
    public void createHashValue(String name, HashMap<String, String> map) {
        stringRedisTemplate.boundHashOps(name).putAll(map);
    }

    /**
     * 获取键对应的hash值
     *
     * @param name 名称
     * @param key  键
     * @return 对应值
     */
    @Override
    public String getHashValue(String name, String key) {
        return (String) stringRedisTemplate.boundHashOps(name).get(key);
    }

    /**
     * 删除键对应的hash值
     *
     * @param key  键
     * @param name 名称
     */
    @Override
    public void deleteHashValue(String name, String key) {
        stringRedisTemplate.boundHashOps(name).delete(key);
    }

    /**
     * 批量寻找关键词并删除
     *
     * @param key 关键词
     */
    @Override
    public void deleteKeys(String key) {
        Set<String> keys = stringRedisTemplate.keys(key + "*");
        if (keys != null) {
            stringRedisTemplate.delete(keys);
        }
    }

}
