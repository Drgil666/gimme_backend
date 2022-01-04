package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.ToDoList;
import com.project.gimme.service.ToDoListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author DrGilbert
 * @date 2022/1/4 16:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class ToDoListServiceImplTest {
    @Resource
    private ToDoListService toDoListService;

    @Test
    public void createTodo() {
        ToDoList toDo = new ToDoList();
        toDo.setStartTime(new Date());
        toDo.setEndTime(new Date());
        toDo.setOwnerId(1);
        toDo.setText("111");
        System.out.println(toDoListService.createTodoList(toDo));
    }

    @Test
    public void updateToDo() {
        ToDoList toDo = toDoListService.getTodoList(1);
        toDo.setText("222");
        System.out.println(toDoListService.updateToDoList(toDo));
    }

    @Test
    public void getTodo() {
        ToDoList toDo = toDoListService.getTodoList(1);
        System.out.println(toDo.getText());
    }

    @Test
    public void deleteTodo() {
    }
}