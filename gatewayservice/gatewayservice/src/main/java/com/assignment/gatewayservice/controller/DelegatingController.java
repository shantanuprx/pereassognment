package com.assignment.gatewayservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.gatewayservice.util.ServiceLocator;

@RestController
public class DelegatingController<T> {

	@Autowired
	private ServiceLocator<T> serviceLocator;
	
	@GetMapping("/{serviceName}/fetch/**")
	public ResponseEntity<T> getDetaislsOfProduct(@PathVariable("serviceName") String serviceName, @RequestBody Map<String, Object> requestMap) {
		return serviceLocator.locateServiceBean(serviceName).getDetails(requestMap);
	}

	@PostMapping("/{serviceName}/add/**")
	public ResponseEntity<T> addProduct(@PathVariable("serviceName") String serviceName, @RequestBody Map<String, Object> requestMap) {
		return serviceLocator.locateServiceBean(serviceName).addDetails(requestMap);
	}

	@PutMapping("/{serviceName}/udpate/**")
	public ResponseEntity<T> updateProduct(@PathVariable("serviceName") String serviceName, @RequestBody Map<String, Object> requestMap) {
		return serviceLocator.locateServiceBean(serviceName).updateDetails(requestMap);
	}

	@DeleteMapping("/{serviceName}/delete/**")
	public ResponseEntity<T> deleteProduct(@PathVariable("serviceName") String serviceName, @RequestBody Map<String, Object> requestMap) {
		return serviceLocator.locateServiceBean(serviceName).deleteDetails(requestMap);
	}
}
