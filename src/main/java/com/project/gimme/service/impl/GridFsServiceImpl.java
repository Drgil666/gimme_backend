package com.project.gimme.service.impl;

import com.project.gimme.dao.GridFsDao;
import com.project.gimme.service.GridFsService;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

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
     * @param file 文件
     * @return 对应文件的mongoId
     * @throws IOException
     */
    @Override
    public String createFile(MultipartFile file) throws IOException {
        return gridFsDao.createFile(file);
    }

    /**
     * 获取文件
     *
     * @param mongoId 文件的mongoId
     * @return 对应的文件
     * @throws IOException
     */
    @Override
    public GridFsResource getFile(String mongoId) throws IOException {
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
