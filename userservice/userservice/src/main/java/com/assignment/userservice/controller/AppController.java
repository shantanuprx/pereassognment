package com.assignment.userservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.userservice.constants.GatewayServiceConstants;
import com.assignment.userservice.security.service.AuthorizationService;
import com.assignment.userservice.util.ServiceLocator;

/**
 * Controller acting as a middleware router for all the services
 * 
 * @param <T>
 */
@RestController
@Validated
public class AppController<T> {

	@Autowired
	private ServiceLocator<T> serviceLocator;

	@Autowired
	private AuthorizationService<T> authorizationService;

	@GetMapping("/{serviceName}/**")
	public ResponseEntity<T> getDetails(@PathVariable("serviceName") String serviceName,
			@RequestBody Map<String, Object> requestMap, @RequestHeader Map<String, String> headers) throws Exception {
		supplyTokenValues(requestMap, headers);
		return serviceLocator.locateServiceBean(serviceName).getDetails(requestMap);
	}

	@PostMapping("/{serviceName}/**")
	public ResponseEntity<T> addDetails(@PathVariable("serviceName") String serviceName,
			@RequestBody Map<String, Object> requestMap, @RequestHeader Map<String, String> headers) throws Exception {
		supplyTokenValues(requestMap, headers);
		return serviceLocator.locateServiceBean(serviceName).addDetails(requestMap);
	}

	@PutMapping("/{serviceName}/**")
	public ResponseEntity<T> udpateDetails(@PathVariable("serviceName") String serviceName,
			@RequestBody Map<String, Object> requestMap, @RequestHeader Map<String, String> headers) throws Exception {
		supplyTokenValues(requestMap, headers);
		return serviceLocator.locateServiceBean(serviceName).updateDetails(requestMap);
	}

	@DeleteMapping("/{serviceName}/**")
	public ResponseEntity<T> deleteDetails(@PathVariable("serviceName") String serviceName,
			@RequestBody Map<String, Object> requestMap, @RequestHeader Map<String, String> headers) throws Exception {
		supplyTokenValues(requestMap, headers);
		return serviceLocator.locateServiceBean(serviceName).deleteDetails(requestMap);
	}

	@GetMapping("/{serviceName}/validate")
	public ResponseEntity<T> validateDetails(@PathVariable("serviceName") String serviceName,
			@RequestBody Map<String, Object> requestMap, @RequestHeader Map<String, String> headers) throws Exception {
		supplyTokenValues(requestMap, headers);
		return serviceLocator.locateServiceBean(serviceName).validateDetails(requestMap);
	}

	private void supplyTokenValues(Map<String, Object> requestMap, Map<String, String> headers) {
		requestMap.put(GatewayServiceConstants.TOKEN, headers.get(GatewayServiceConstants.TOKEN));
		authorizationService.validateToken(requestMap);
	}
}
