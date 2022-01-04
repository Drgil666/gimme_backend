package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.Channel;
import com.project.gimme.service.ChannelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 10:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class ChannelServiceImplTest {
    @Resource
    private ChannelService channelService;

    @Test
    public void createChannel() {
        Channel channel = new Channel();
        channel.setCreateTime(new Date());
        channel.setNick("test channel");
        channel.setOwnerId(1);
        System.out.println(channelService.createChannel(channel));
    }

    @Test
    public void updateChannel() {
        Channel channel = channelService.getChannel(1);
        channel.setNick("222");
        System.out.println(channelService.updateChannel(channel));
    }

    @Test
    public void getChannel() {
        Channel channel = channelService.getChannel(1);
        System.out.println(channel.getNick());
    }

    @Test
    public void deleteChannel() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        System.out.println(channelService.deleteChannel(idList));
    }
}