package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.GroupNotice;
import com.project.gimme.service.GroupNoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author DrGilbert
 * @date 2022/1/4 9:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class GroupNoticeServiceImplTest {
    @Resource
    private GroupNoticeService groupNoticeService;

    @Test
    public void createGroupNotice() {
        GroupNotice groupNotice = new GroupNotice();
        groupNotice.setOwnerId(2);
        groupNotice.setGroupId(1);
        groupNotice.setText("test notice");
        System.out.println(groupNoticeService.createGroupNotice(groupNotice));
    }

    @Test
    public void updateGroupNotice() {
        GroupNotice groupNotice = groupNoticeService.getGroupNotice(1);
        groupNotice.setText("222");
        System.out.println(groupNoticeService.updateGroupNotice(groupNotice));
    }

    @Test
    public void getGroupNotice() {
        GroupNotice groupNotice = groupNoticeService.getGroupNotice(1);
        System.out.println(groupNotice.getText());
    }

    @Test
    public void deleteGroupNotice() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        System.out.println(groupNoticeService.deleteGroupNotice(2, idList));
    }
}