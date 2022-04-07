package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.PersonalMsg;
import com.project.gimme.service.PersonalMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class PersonalMsgServiceImplTest {
    @Resource
    private PersonalMsgService personalMsgService;

    @Test
    public void createPersonalMsg() {
        PersonalMsg personalMsg = new PersonalMsg();
        personalMsg.setOwnerId(1);
        personalMsg.setOperatorId(2);
        personalMsg.setObjectId(2);
        personalMsg.setObjectType("test");
        System.out.println(personalMsgService.createPersonalMsg(personalMsg));
    }

    @Test
    public void updatePersonalMsg() {
        PersonalMsg personalMsg = personalMsgService.getPersonalMsg(1);
        personalMsg.setObjectType("test");
        System.out.println(personalMsgService.updatePersonalMsg(personalMsg));
    }

    @Test
    public void getPersonalMsg() {
        PersonalMsg personalMsg = personalMsgService.getPersonalMsg(1);
        System.out.println(personalMsg.getObjectType());
    }
}