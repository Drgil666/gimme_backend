package com.project.gimme.service;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/2 13:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class UserServiceImplTest {
    @Resource
    private UserService userService;

    @Test
    public void createUser() {
        User user = new User();
        user.setBirthday(new Date());
        user.setAvatar("111");
        user.setCity(1);
        user.setCompany(1);
        user.setCountry(1);
        user.setMail("111");
        user.setMotto("111");
        user.setNick("test1");
        user.setOccupation(1);
        user.setProvince(1);
        System.out.println(userService.createUser(user));
    }
}