package com.project.gimme.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.project.gimme.dao.GridFsDao;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:19
 */
public class GridFsDaoImpl implements GridFsDao {
    @Resource
    private GridFsTemplate gridFsTemplate;

    /**
     * 上传文件
     *
     * @param file     要上传的文件
     * @param filename 存储文件名
     * @return 文件id
     * @throws IOException IO异常
     */
    @Override
    public String uploadFile(MultipartFile file, String filename) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("createTime", new Date());
        InputStream inputStream = file.getInputStream();
        if (StringUtils.isEmpty(filename)) {
            filename = file.getOriginalFilename();
        }
        ObjectId objectId = gridFsTemplate.store(inputStream, filename, metaData);
        return objectId.toHexString();
        //ToDoList:文件相关功能未测试
    }

    /**
     * 获取文件
     *
     * @param fileName 要获取的文件名
     * @return 对应的文件
     */
    @Override
    public GridFSFile getFile(String fileName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(fileName));
        GridFSFile file = gridFsTemplate.findOne(query);
        return file;
    }
}
