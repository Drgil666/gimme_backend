package com.project.gimme.service.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.pojo.ChatFile;
import com.project.gimme.service.ChatFileService;
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
public class ChatFileServiceImplTest {
    @Resource
    private ChatFileService chatFileService;

    @Test
    public void createGroupFile() {
        ChatFile chatFile = new ChatFile();
        chatFile.setObjectId(1);
        chatFile.setFilename("111.txt");
        chatFile.setMongoId("1111");
        chatFile.setOwnerId(1);
        System.out.println(chatFileService.createChatFile(chatFile));
    }

    @Test
    public void updateGroupFile() {
        ChatFile chatFile = chatFileService.getChatFile(1);
        chatFile.setFilename("222.txt");
        System.out.println(chatFileService.updateChatFile(chatFile));
    }

    @Test
    public void getGroupFile() {
        ChatFile chatFile = chatFileService.getChatFile(1);
        System.out.println(chatFile.getFilename());
    }

    @Test
    public void getGroupByGroupId() {
        List<ChatFile> chatFileList = chatFileService.getChatFileByGroupId(1, 1, "");
        for (ChatFile chatFile : chatFileList) {
            System.out.println(chatFile.getId());
        }
    }
}