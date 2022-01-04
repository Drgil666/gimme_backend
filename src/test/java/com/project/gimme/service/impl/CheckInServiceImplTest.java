package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.CheckIn;
import com.project.gimme.service.CheckInService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author DrGilbert
 * @date 2022/1/4 15:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class CheckInServiceImplTest {
    @Resource
    private CheckInService checkInService;

    @Test
    public void createCheckIn() {
        CheckIn checkIn = new CheckIn();
        checkIn.setAddress("111");
        checkIn.setGroupId(3);
        checkIn.setType(1);
        System.out.println(checkInService.createCheckIn(checkIn));
    }

    @Test
    public void updateCheckIn() {
        CheckIn checkIn = checkInService.getCheckIn(1);
        checkIn.setAddress("222");
        System.out.println(checkInService.updateCheckIn(checkIn));
    }

    @Test
    public void getCheckIn() {
        CheckIn checkIn = checkInService.getCheckIn(1);
        System.out.println(checkIn.getAddress());
    }
}