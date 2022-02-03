package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.service.PersonalMsgUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author DrGilbert
 * @date 2022/2/3 16:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class PersonalMsgUserServiceImplTest {
    @Resource
    private PersonalMsgUserService personalMsgUserService;

    @Test
    public void createPersonalMsgUser() {

    }

    @Test
    public void updatePersonalMsgUser() {
    }

    @Test
    public void getPersonalMsgUser() {
    }

    @Test
    public void getPersonalMsgUserList() {
    }
}