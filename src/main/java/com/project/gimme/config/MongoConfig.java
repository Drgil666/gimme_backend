package com.project.gimme.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * @author Gilbert
 * @date 2022/2/24 15:54
 */
public class MongoConfig {
    @Value("${grid.fs.name}")
    private String bucketName;

    @Bean
    public GridFsTemplate gridFsTemplate(MongoDbFactory dbFactory, MongoConverter converter) {
        return new GridFsTemplate(dbFactory, converter, bucketName);
    }
}
