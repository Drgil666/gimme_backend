package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.GroupFile;
import com.project.gimme.service.GroupFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DrGilbert
 * @date 2022/1/4 17:06
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class GroupFileServiceImplTest {
    @Resource
    private GroupFileService groupFileService;

    @Test
    public void createGroupFile() {
        GroupFile groupFile = new GroupFile();
        groupFile.setGroupId(1);
        groupFile.setFilename("111.txt");
        groupFile.setMongoId("1111");
        groupFile.setOwnerId(1);
        System.out.println(groupFileService.createGroupFile(groupFile));
    }

    @Test
    public void updateGroupFile() {
        GroupFile groupFile = groupFileService.getGroupFile(1);
        groupFile.setFilename("222.txt");
        System.out.println(groupFileService.updateGroupFile(groupFile));
    }

    @Test
    public void getGroupFile() {
        GroupFile groupFile = groupFileService.getGroupFile(1);
        System.out.println(groupFile.getFilename());
    }

    @Test
    public void getGroupByGroupId() {
        List<GroupFile> groupFileList = groupFileService.getGroupByGroupId(1, "");
        for (GroupFile groupFile : groupFileList) {
            System.out.println(groupFile.getId());
        }
    }
}