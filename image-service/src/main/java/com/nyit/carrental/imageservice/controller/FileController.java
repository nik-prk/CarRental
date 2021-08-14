package com.nyit.carrental.imageservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nyit.carrental.imageservice.domain.FileDetailsDTO;
import com.nyit.carrental.imageservice.exception.FileException;
import com.nyit.carrental.imageservice.service.DeleteFileService;
import com.nyit.carrental.imageservice.service.FindFileService;
import com.nyit.carrental.imageservice.service.SaveFileDataService;

@RestController
public class FileController {

	@Autowired
	SaveFileDataService saveFileDataService;

	@Autowired
	FindFileService findFileService;
	
	@Autowired
	DeleteFileService deleteFileService;

	@RequestMapping(value = "/addFile", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
	public ResponseEntity<FileDetailsDTO> addFile(@RequestBody MultipartFile dataFile,
			@RequestParam(required = true) String relationId) throws FileException {
		return new ResponseEntity<>(saveFileDataService.executeFileService(dataFile, relationId), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/findFile", method = RequestMethod.GET)
	public ResponseEntity<Resource> findFile(@RequestParam(required = true) String fileId,
			@RequestParam(required = true) String relationId) throws FileException, IOException {
		GridFsResource resource = findFileService.executeFileService(fileId, relationId);
		return (resource!=null) ? ResponseEntity.ok().contentType(MediaType.parseMediaType(resource.getContentType()))
				.contentLength(resource.contentLength()).body(resource) : null;
	}
	
	@RequestMapping(value = "/deleteFile", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteFile(@RequestParam(required = true) String fileId,
			@RequestParam(required = true) String relationId) throws FileException, IOException {
		return new ResponseEntity<>(deleteFileService.executeFileService(fileId, relationId), HttpStatus.OK);
	}

}
