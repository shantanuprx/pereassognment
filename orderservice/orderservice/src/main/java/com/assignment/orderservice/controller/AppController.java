package com.assignment.orderservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.orderservice.constants.GatewayServiceConstants;
import com.assignment.orderservice.security.service.AuthorizationService;
import com.assignment.orderservice.util.ServiceLocator;

/**
 * Controller acting as a middleware router for all the services
 * 
 * @param <T>
 */
@RestController
public class AppController<T> {

	@Autowired
	private ServiceLocator<T> serviceLocator;

	@Autowired
	private AuthorizationService<T> authorizationService;

	@GetMapping("/{service}")
	public ResponseEntity<T> getDetails(@RequestBody Map<String, Object> requestBody, @PathVariable String service,
			@RequestHeader Map<String, String> requestHeaders) throws Exception {
		supplyTokenValues(requestBody, requestHeaders);
		return serviceLocator.locateServiceBean(service).getDetails(requestBody);
	}

	@PostMapping("/{service}")
	public ResponseEntity<T> addDetails(@RequestBody Map<String, Object> requestBody, @PathVariable String service,
			@RequestHeader Map<String, String> requestHeaders) throws Exception {
		supplyTokenValues(requestBody, requestHeaders);
		return serviceLocator.locateServiceBean(service).addDetails(requestBody);
	}

	@PutMapping("/{service}")
	public ResponseEntity<T> updateDetails(@RequestBody Map<String, Object> requestBody, @PathVariable String service,
			@RequestHeader Map<String, String> requestHeaders) throws Exception {
		supplyTokenValues(requestBody, requestHeaders);
		return serviceLocator.locateServiceBean(service).updateDetails(requestBody);
	}

	@DeleteMapping("/{service}")
	public ResponseEntity<T> deleteDetails(@RequestBody Map<String, Object> requestBody, @PathVariable String service,
			@RequestHeader Map<String, String> requestHeaders) throws Exception {
		supplyTokenValues(requestBody, requestHeaders);
		return serviceLocator.locateServiceBean(service).deleteDetails(requestBody);
	}

	private void supplyTokenValues(Map<String, Object> requestMap, Map<String, String> headers) {
		requestMap.put(GatewayServiceConstants.TOKEN, headers.get(GatewayServiceConstants.TOKEN));
		authorizationService.validateToken(requestMap);
	}
}
