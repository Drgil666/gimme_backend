package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.GroupUser;
import com.project.gimme.service.GroupUserService;
import com.project.gimme.utils.AuthorizeUtil;
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
        groupUser.setGroupId(1);
        groupUser.setUserId(1);
        groupUser.setType(AuthorizeUtil.Character.TYPE_USER.getCode());
        System.out.println(groupUserService.createGroupUser(groupUser));
    }

    @Test
    public void updateGroupUser() {
        GroupUser groupUser = groupUserService.getGroupUser(1, 1);
        groupUser.setType(1);
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