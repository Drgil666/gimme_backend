package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.ChannelNotice;
import com.project.gimme.service.ChannelNoticeService;
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
 * @date 2022/1/4 11:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class ChannelNoticeServiceImplTest {
    @Resource
    private ChannelNoticeService channelNoticeService;

    @Test
    public void createChannelNotice() {
        ChannelNotice channelNotice = new ChannelNotice();
        channelNotice.setChannelId(3);
        channelNotice.setType("test");
        channelNotice.setCreateTime(new Date());
        System.out.println(channelNoticeService.createChannelNotice(channelNotice));
    }

    @Test
    public void updateChannelNotice() {
        ChannelNotice channelNotice = channelNoticeService.getChannelNotice(null, 2);
        channelNotice.setText("222");
        System.out.println(channelNoticeService.updateChannelNotice(channelNotice));
    }

    @Test
    public void getChannelNotice() {
        ChannelNotice channelNotice = channelNoticeService.getChannelNotice(null, 2);
        System.out.println(channelNotice.getText());
    }

    @Test
    public void deleteChannelNotice() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        System.out.println(channelNoticeService.deleteChannelNotice(3, idList));
    }
}