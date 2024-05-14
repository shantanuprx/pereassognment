package com.assignment.ecommerceweb.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil<T> {

	public ResponseEntity<T> prepareResponse(T responseDto, HttpStatus statusCode){
		return ResponseEntity.status(statusCode).body(responseDto);
	}
}
