package com.assignment.catalogservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.catalogservice.service.CatalogService;

@RestController
public class AppController<T> {

	@Autowired
	private CatalogService<T> catalogService;

	@GetMapping("/fetch")
	public ResponseEntity<T> getDetails(@RequestParam String query, @RequestParam int pageNumber) {
		return catalogService.getDetails(query, pageNumber);
	}

	@PostMapping("/add")
	public ResponseEntity<T> addDetails(@RequestBody Map<String, Object> requestBody) {
		return catalogService.addDetails(requestBody);
	}

	@PutMapping("/update")
	public ResponseEntity<T> updateDetails(@RequestBody Map<String, Object> requestBody) {
		return catalogService.updateDetails(requestBody);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<T> deleteDetails(@RequestBody Map<String, Object> requestBody) {
		return catalogService.deleteDetails(requestBody);
	}
}
