package com.project.gimme.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Gilbert
 * @date 2022/2/24 16:04
 */
public interface GridFsService {
    /**
     * 上传文件
     *
     * @param file     文件
     * @param fileName 文件名
     * @param fileType 文件类型
     * @return 对应文件的mongoId
     * @throws IOException
     */
    String createFile(MultipartFile file, String fileName, String fileType) throws IOException;

    /**
     * 获取文件
     *
     * @param mongoId 文件的mongoId
     * @return 对应的文件
     */
    GridFSFile getFile(String mongoId);

    /**
     * 删除文件
     *
     * @param mongoId 文件的mongoId
     */
    void deleteFile(String mongoId);
}
