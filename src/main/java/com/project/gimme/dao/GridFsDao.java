package com.project.gimme.dao;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:13
 */
public interface GridFsDao {
    /**
     * 上传文件
     *
     * @param file     要上传的文件
     * @param filename 存储文件名
     * @param fileType 文件类型
     * @return 文件id
     * @throws IOException IO异常
     */
    String createFile(MultipartFile file, String filename, String fileType) throws IOException;

    /**
     * 获取文件
     *
     * @param mongoId 要获取的文件名
     * @return 对应的文件
     * @throws IOException IO异常
     */
    InputStream getFile(String mongoId) throws IOException;

    /**
     * 批量删除文件
     *
     * @param mongoId 文件id列表
     */
    void deleteFile(String mongoId);
}
