package com.jeanwolff.cursomc.resources.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jeanwolff.cursomc.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	public ResourceExceptionHandler() {
	}
	

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), new Date(System.currentTimeMillis()) );

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
