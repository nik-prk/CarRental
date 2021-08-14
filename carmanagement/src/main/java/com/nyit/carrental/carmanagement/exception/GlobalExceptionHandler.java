package com.nyit.carrental.carmanagement.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(VehicleException.class)
	public @ResponseBody ResponseEntity<ExceptionJSONInfo> handleContactSystemException(HttpServletRequest request, VehicleException ex){
		logger.error(ex.getErrorCode() + " {}",ex);
		ExceptionJSONInfo response = new ExceptionJSONInfo(ex.getErrorCode(),ex.getErrorMessage());
		return new ResponseEntity<ExceptionJSONInfo>(response,HttpStatus.valueOf(response.getStatusCode()));
	}
	
	/*@ExceptionHandler(HystrixFlowException.class)
	public @ResponseBody ResponseEntity<ExceptionJSONInfo> handleHystrixSystemException(HttpServletRequest request, HystrixFlowException ex){
		//logger.error(ex.getExceptionCode() + " {}",ex);
		ExceptionJSONInfo response = new ExceptionJSONInfo("CONFLICT",ex.getMessage());
		return new ResponseEntity<ExceptionJSONInfo>(response,HttpStatus.valueOf(response.getStatusCode()));
	}*/
	
}
