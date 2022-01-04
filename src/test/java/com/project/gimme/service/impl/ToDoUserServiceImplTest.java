package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.ToDoUser;
import com.project.gimme.service.ToDoUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 16:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class ToDoUserServiceImplTest {
    @Resource
    private ToDoUserService toDoUserService;

    @Test
    public void createToDoUser() {
        ToDoUser toDoUser = new ToDoUser();
        toDoUser.setUserId(1);
        toDoUser.setStatus(1);
        toDoUser.setToDoId(1);
        System.out.println(toDoUserService.createToDoUser(toDoUser));
    }

    @Test
    public void updateToDoUser() {
        ToDoUser toDoUser = toDoUserService.getToDoUser(1, 1);
        toDoUser.setStatus(2);
        System.out.println(toDoUserService.updateToDoUser(toDoUser));
    }

    @Test
    public void getToDoUser() {
        ToDoUser toDoUser = toDoUserService.getToDoUser(1, 1);
        System.out.println(toDoUser.getStatus());
    }

    @Test
    public void deleteGroupUser() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        System.out.println(toDoUserService.deleteGroupUser(1, idList));
    }
}