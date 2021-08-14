package com.nyit.carrental.imageservice.service;

import com.nyit.carrental.imageservice.exception.FileException;

public interface FileService<S,T> {
	
	public T executeFileService(S req, String relationId) throws FileException;

}
