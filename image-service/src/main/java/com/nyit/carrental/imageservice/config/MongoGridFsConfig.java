package com.nyit.carrental.imageservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoGridFsConfig extends AbstractMongoConfiguration{
	@Value("${spring.data.mongodb.host}")
	private String hostUrl;
	@Value("${spring.data.mongodb.database}")
	private String dbName;
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
	    return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

	@Override
	public MongoClient mongoClient() {
		return new MongoClient(hostUrl);
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return dbName;
	}

}
