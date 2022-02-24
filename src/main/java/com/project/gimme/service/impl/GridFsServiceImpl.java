package com.project.gimme.service.impl;

import com.project.gimme.dao.GridFsDao;
import com.project.gimme.service.GridFsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Gilbert
 * @date 2022/2/24 16:11
 */
@Service
public class GridFsServiceImpl implements GridFsService {
    @Resource
    private GridFsDao gridFsDao;

    /**
     * 上传文件
     *
     * @param file     文件
     * @param fileName 文件名
     * @param fileType 文件类型
     * @return 对应文件的mongoId
     * @throws IOException
     */
    @Override
    public String createFile(MultipartFile file, String fileName, String fileType) throws IOException {
        return gridFsDao.createFile(file, fileName, fileType);
    }

    /**
     * 获取文件
     *
     * @param mongoId 文件的mongoId
     * @return 对应的文件
     * @throws IOException
     */
    @Override
    public InputStream getFile(String mongoId) throws IOException {
        return gridFsDao.getFile(mongoId);
    }

    /**
     * 删除文件
     *
     * @param mongoId 文件的mongoId
     */
    @Override
    public void deleteFile(String mongoId) {
        gridFsDao.deleteFile(mongoId);
    }
}
