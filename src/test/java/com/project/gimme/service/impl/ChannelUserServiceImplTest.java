package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.ChannelUser;
import com.project.gimme.service.ChannelUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 11:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class ChannelUserServiceImplTest {
    @Resource
    private ChannelUserService channelUserService;

    @Test
    public void createChannelUser() {
        ChannelUser channelUser = new ChannelUser();
        channelUser.setChannelId(3);
        channelUser.setUserId(1);
        channelUser.setChannelNick("channelNick1");
        System.out.println(channelUserService.createChannelUser(channelUser));
    }

    @Test
    public void updateChannelUser() {
        ChannelUser channelUser = channelUserService.getChannelUser(3, 1);
        channelUser.setChannelNick("channelNick2");
        System.out.println(channelUserService.updateChannelUser(channelUser));
    }

    @Test
    public void getChannelUser() {
        ChannelUser channelUser = channelUserService.getChannelUser(3, 1);
        System.out.println(channelUser.getChannelNick());
    }

    @Test
    public void deleteChannelUser() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        System.out.println(channelUserService.deleteChannelUser(3, idList));
    }
}