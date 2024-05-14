package com.assignment.userservice.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface BaseService<T> {

	public ResponseEntity<T> getDetails(Map<String, Object> requestData);

	public ResponseEntity<T> addDetails(Map<String, Object> requestData);

	public ResponseEntity<T> updateDetails(Map<String, Object> requestData);

	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData);

}
