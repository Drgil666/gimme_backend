package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.CheckInUser;
import com.project.gimme.service.CheckInUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class CheckInUserServiceImplTest {
    @Resource
    private CheckInUserService checkInUserService;

    @Test
    public void createCheckInUser() {
        CheckInUser checkInUser = new CheckInUser();
        checkInUser.setUserId(1);
        checkInUser.setTimestamp(new Date());
        checkInUser.setCheckInId(1);
        System.out.println(checkInUserService.createCheckInUser(checkInUser));
    }

    @Test
    public void updateCheckInUser() {
        CheckInUser checkInUser = checkInUserService.getCheckInUser(1, 1);
        checkInUser.setTimestamp(new Date());
        System.out.println(checkInUserService.updateCheckInUser(checkInUser));
    }

    @Test
    public void getCheckInUser() {
        CheckInUser checkInUser = checkInUserService.getCheckInUser(1, 1);
        System.out.println(checkInUser.getTimestamp());
    }
}