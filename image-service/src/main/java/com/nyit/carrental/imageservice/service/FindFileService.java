package com.nyit.carrental.imageservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.nyit.carrental.imageservice.exception.FileException;

@Service
public class FindFileService implements FileService<String, GridFsResource> {

	@Autowired
	GridFsTemplate gridFsTemplate;
	
	@Autowired
	MongoDbFactory mongoDbFactory;

	@Override
	public GridFsResource executeFileService(String req, String relationId) throws FileException {
		Query query = new Query();
		query.addCriteria(Criteria.where("metadata.relationId").is(relationId));
		query.addCriteria(Criteria.where("_id").is(req));
		GridFSFile gridFSFile = gridFsTemplate.findOne(query);
		return (gridFSFile != null) ? new GridFsResource(gridFSFile,getGridFs().openDownloadStream(gridFSFile.getObjectId())) : null;
	}
	
	private GridFSBucket getGridFs() {
	    MongoDatabase db = mongoDbFactory.getDb();
	    return GridFSBuckets.create(db);
	}

}
