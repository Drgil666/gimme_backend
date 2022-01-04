package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.service.ChatMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class ChatMsgServiceImplTest {
    @Resource
    private ChatMsgService chatMsgService;

    @Test
    public void createChatMsg() {
    }

    @Test
    public void updateChatMsg() {
    }

    @Test
    public void getChannelUser() {
    }

    @Test
    public void getChannelUserListByObjectId() {
    }

    @Test
    public void deleteChatMsg() {
    }
}