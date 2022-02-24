package com.project.gimme.dao.impl;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.project.gimme.GimmeApplication;
import com.project.gimme.dao.GridFsDao;
import com.project.gimme.utils.BucketUtil;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
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
        File file = new File("D://123.txt");
        String fileName = "1231.txt";
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName,
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        System.out.println(gridFsDao.createFile(multipartFile, fileName, BucketUtil.BUCKET_FILE_ATTRIBUTE));
    }

    @Test
    public void getFile() {
        String id = "62173fa947d75c4927e011d2";
        GridFSFile file = gridFsDao.getFile(id);
        System.out.println(file.getFilename() + " " + file.getMetadata().toJson());
    }
}