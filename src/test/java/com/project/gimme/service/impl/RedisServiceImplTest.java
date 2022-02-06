package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author DrGilbert
 * @date 2022/2/6 23:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class RedisServiceImplTest {
    @Resource
    private RedisService redisService;

    @Test
    public void createUserLoginToken() {
        System.out.println(redisService.createUserLoginToken(2));
    }

    @Test
    public void checkUserLoginToken() {
        System.out.println(redisService.checkUserLoginToken("9daeb2e1-24d3-4f15-8b15-8b5a2ad41ef4"));
    }

    @Test
    public void createFriendToken() {
    }

    @Test
    public void checkFriendToken() {
    }

    @Test
    public void createGroupAuthorityToken() {
    }

    @Test
    public void getGroupAuthorityToken() {
    }

    @Test
    public void testCreateUserLoginToken() {
    }

    @Test
    public void testCheckUserLoginToken() {
    }

    @Test
    public void getUserId() {
    }

    @Test
    public void testCreateFriendToken() {
    }

    @Test
    public void testCheckFriendToken() {
    }

    @Test
    public void testCreateGroupAuthorityToken() {
    }

    @Test
    public void testGetGroupAuthorityToken() {
    }
}