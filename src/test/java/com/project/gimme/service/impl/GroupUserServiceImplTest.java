package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.GroupUser;
import com.project.gimme.service.GroupUserService;
import com.project.gimme.utils.UserUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author DrGilbert
 * @date 2022/1/3 11:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class GroupUserServiceImplTest {
    @Resource
    private GroupUserService groupUserService;

    @Test
    public void createGroupUser() {
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupId(3);
        groupUser.setUserId(1);
        groupUser.setGroupNick("nick1");
        groupUser.setType(UserUtil.UserCharacter.TYPE_USER.getName());
        System.out.println(groupUserService.createGroupUser(groupUser));
    }

    @Test
    public void updateGroupUser() {
        GroupUser groupUser = groupUserService.getGroupUser(3, 1);
        groupUser.setType("test");
        groupUser.setGroupNick("nick2");
        System.out.println(groupUserService.updateGroupUser(groupUser));
    }

    @Test
    public void getGroupUser() {
        GroupUser groupUser = groupUserService.getGroupUser(1, 1);
        System.out.println(groupUser.getType());
    }

    @Test
    public void deleteGroupUser() {
        List<Integer> idList = new ArrayList<>();
        idList.add(2);
        System.out.println(groupUserService.deleteGroupUser(1, idList));
    }
}