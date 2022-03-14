package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.mapper.ChatMsgMapper;
import com.project.gimme.pojo.vo.MessageVO;
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
    @Resource
    private ChatMsgMapper chatMsgMapper;

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

    @Test
    public void testCreateChatMsg() {
    }

    @Test
    public void testUpdateChatMsg() {
    }

    @Test
    public void getChatMsg() {
    }

    @Test
    public void getChatMsgListByObjectId() {
    }

    @Test
    public void testDeleteChatMsg() {
    }

    @Test
    public void getMessageVoByUserId() {
        Integer userId = 2;
        Integer objectId = 3;
        Integer type = 0;
        MessageVO messageVO = chatMsgMapper.getFriendMessageVoByObjectId(userId, objectId);
        System.out.println(messageVO.toString());
    }

    @Test
    public void checkValidity() {
    }
}