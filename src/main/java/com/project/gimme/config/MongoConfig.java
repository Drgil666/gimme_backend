package com.project.gimme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * @author Gilbert
 * @date 2022/2/24 15:54
 */
@Configuration
public class MongoConfig {

    @Bean
    public GridFsTemplate gridFsTemplate(MongoDatabaseFactory databaseFactory, MongoConverter converter) {
        return new GridFsTemplate(databaseFactory, converter, "gimme");
    }

}

