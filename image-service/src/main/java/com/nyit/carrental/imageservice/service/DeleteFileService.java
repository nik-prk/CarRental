package com.nyit.carrental.imageservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.nyit.carrental.imageservice.exception.FileException;

@Service
public class DeleteFileService implements FileService<String, Boolean>{
	
	@Autowired
	GridFsTemplate gridFsTemplate;
	
	@Override
	public Boolean executeFileService(String req, String relationId) throws FileException {
		Query query = new Query();
		query.addCriteria(Criteria.where("metadata.relationId").is(relationId));
		query.addCriteria(Criteria.where("_id").is(req));
		gridFsTemplate.delete(query);
		return true;
	}


}
