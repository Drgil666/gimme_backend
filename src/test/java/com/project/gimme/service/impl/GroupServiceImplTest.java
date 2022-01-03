package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.Group;
import com.project.gimme.service.GroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author DrGilbert
 * @date 2022/1/3 10:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class GroupServiceImplTest {
    @Resource
    private GroupService groupService;

    @Test
    public void createGroup() {
        Group group = new Group();
        group.setNick("222");
        groupService.createGroup(group);
        System.out.println(group.getId());
    }

    @Test
    public void updateGroup() {
        Group group = groupService.getGroup(1);
        group.setNick("222");
        System.out.println(groupService.updateGroup(group));
    }

    @Test
    public void getGroup() {
        Group group = groupService.getGroup(1);
        System.out.println(group.getNick());
    }

    @Test
    public void getGroupByIdAndNick() {
        List<Group> groupList = groupService.getGroupByIdAndNick("2");
        for (Group group : groupList) {
            System.out.println(group.getId());
        }
    }
}