package com.nyit.carrental.imageservice.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nyit.carrental.imageservice.domain.FileDetailsDTO;
import com.nyit.carrental.imageservice.exception.FileException;
import com.nyit.carrental.imageservice.util.ImageResizeUtil;

@Service
public class SaveFileDataService implements FileService<MultipartFile, FileDetailsDTO>{
	
	@Autowired
	GridFsTemplate gridFsTemplate;

	@Override
	public FileDetailsDTO executeFileService(MultipartFile req, String relationId) throws FileException {
		InputStream inputStream = null;
		if(null!=req) {
		try {
			if(req.getContentType().contains("image")) {
			File file = ImageResizeUtil.resize(req, 300, 300);
			inputStream = new FileInputStream(file);
			}else {
				inputStream = new BufferedInputStream(req.getInputStream());
			}
		} catch (IOException e) {
			throw new FileException(HttpStatus.CONFLICT.name(), "File cannot be converted into Stream");
		}
		Document document = new Document();
		document.put("relationId", relationId);
		String fileId = gridFsTemplate
				.store(inputStream, req.getOriginalFilename(), req.getContentType(), document).toString();
		return new FileDetailsDTO(fileId, relationId, req.getOriginalFilename());
		}
		return null;
	}

}
