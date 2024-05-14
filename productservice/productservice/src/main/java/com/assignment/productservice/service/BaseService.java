package com.assignment.productservice.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface BaseService<T> {

	public ResponseEntity<T> getDetails(Map<String, Object> requestData) throws Exception;

	public ResponseEntity<T> addDetails(Map<String, Object> requestData) throws Exception;

	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) throws Exception;

	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) throws Exception;

}
