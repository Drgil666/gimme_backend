package com.project.gimme.dao.impl;

import com.project.gimme.GimmeApplication;
import com.project.gimme.dao.GridFsDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Gilbert
 * @date 2022/2/24 15:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GimmeApplication.class)
public class GridFsDaoImplTest {
    @Resource
    private GridFsDao gridFsDao;

    @Test
    public void createFile() throws IOException {
//        File file = new File("D://123.txt");
//        String fileName = "1231.txt";
//        FileInputStream fileInputStream = new FileInputStream(file);
//        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName,
//                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
//        System.out.println(gridFsDao.createFile(multipartFile));
    }

    @Test
    public void getFile() throws IOException {
//        String id = "6217420a17a4d4553afee7d1";
//        GridFSFile file = gridFsDao.getFile(id);
//        if (file != null)
//            System.out.println(file.getFilename() + " " + file.getMetadata().toJson());
    }
}