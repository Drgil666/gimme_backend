package com.project.gimme.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.project.gimme.dao.GridFsDao;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static com.project.gimme.utils.BucketUtil.CREATE_TIME_ATTRIBUTE;
import static com.project.gimme.utils.BucketUtil.FILE_TYPE_ATTRIBUTE;

/**
 * @author DrGilbert
 * @date 2022/1/4 12:19
 */
@Component("GridFsDaoImpl")
public class GridFsDaoImpl implements GridFsDao {
    @Resource
    private GridFsTemplate gridFsTemplate;

    /**
     * 上传文件
     *
     * @param file     要上传的文件
     * @param filename 存储文件名
     * @return 文件id
     */
    @Override
    public String createFile(MultipartFile file, String filename, String fileType) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put(CREATE_TIME_ATTRIBUTE, new Date());
        metaData.put(FILE_TYPE_ATTRIBUTE, fileType);
        InputStream inputStream = file.getInputStream();
        if (StringUtils.isEmpty(filename)) {
            filename = file.getOriginalFilename();
        }
        ObjectId objectId = gridFsTemplate.store(inputStream, filename, metaData);
        return objectId.toHexString();
    }

    /**
     * 获取文件
     *
     * @param mongoId 要获取的文件名
     * @return 对应的文件
     * @throws IOException IO异常
     */
    @Override
    public InputStream getFile(String mongoId) throws IOException {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(mongoId));
        GridFSFile file = gridFsTemplate.findOne(query);
        if (file != null) {
            GridFsResource resource = new GridFsResource(file);
            return resource.getInputStream();
        }
        return null;
    }

    /**
     * 批量删除文件
     *
     * @param mongoId 文件id列表
     */
    @Override
    public void deleteFile(String mongoId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(mongoId));
        gridFsTemplate.delete(query);
    }
}
