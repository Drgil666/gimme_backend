package com.project.gimme.dao;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:13
 */
public interface GridFsDao {
    /**
     * 上传文件
     *
     * @param file     要上传的文件
     * @param fileName 存储文件名
     * @return 文件id
     */
    public String uploadFile(MultipartFile file, String fileName) throws IOException;

    /**
     * 获取文件
     *
     * @param fileName 要获取的文件名
     * @return 对应的文件
     */
    public GridFSFile getFile(String fileName);
}
