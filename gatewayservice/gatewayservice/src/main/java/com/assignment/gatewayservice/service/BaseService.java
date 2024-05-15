package com.assignment.gatewayservice.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
/* *
 * Base service class that needs to be implemented by each and every service class
 * implementation of this class will be called from controller.
 * 
 * */

public interface BaseService<T> {

	public ResponseEntity<T> getDetails(Map<String, Object> requestData) throws Exception;

	public ResponseEntity<T> addDetails(Map<String, Object> requestData) throws Exception;

	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) throws Exception;

	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) throws Exception;

}