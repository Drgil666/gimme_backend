package com.project.gimme.service;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Test
    public void updateUser() {
        User user = new User();
        user.setBirthday(new Date());
        user.setAvatar("222");
        user.setCity(2);
        user.setCompany(2);
        user.setCountry(2);
        user.setMail("222");
        user.setMotto("222");
        user.setNick("test2");
        user.setOccupation(1);
        user.setProvince(1);
        user.setId(2);
        System.out.println(userService.updateUser(user));
    }

    @Test
    public void getUser() {
        User user = userService.getUser(2);
        System.out.println(user.getNick());
    }

    @Test
    public void getUserByIdAndNick() {
        List<User> userList = userService.getUserByIdAndNick("2");
        for (User user : userList) {
            System.out.println(user.getId());
        }
    }
}