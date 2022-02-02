package com.project.gimme.dao.impl;

import com.project.gimme.dao.RedisDao;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
    public void setValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取键对应的值
     *
     * @param key 键
     * @return 对应值
     */
    @Override
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除键对应的值
     *
     * @param key 键
     */
    @Override
    public void deleteValue(String key) {
        stringRedisTemplate.delete(key);
    }

}
