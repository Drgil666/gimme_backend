package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.Friend;
import com.project.gimme.service.FriendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author DrGilbert
 * @date 2022/1/12 14:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class FriendServiceImplTest {
    @Resource
    private FriendService friendService;

    @Test
    public void createFriend() {
        Friend friend = new Friend();
        friend.setUserId(1);
        friend.setFriendId(2);
        friend.setFriendNote("note1");
        friendService.createFriend(friend);
        System.out.println(friend.getFriendNote());
    }

    @Test
    public void updateFriend() {
    }

    @Test
    public void getFriend() {
    }

    @Test
    public void deleteFriend() {
    }
}