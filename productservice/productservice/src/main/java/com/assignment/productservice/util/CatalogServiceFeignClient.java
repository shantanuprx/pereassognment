package com.assignment.productservice.util;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

//@FeignClient("catalogservice")
public interface CatalogServiceFeignClient {

	@PostMapping("/catalog/add")
	public <T> ResponseEntity<T> addDetails(Map<String, Object> requestBody);
	
	@PutMapping("/catalog/update")
	public <T> ResponseEntity<T> updateDetails(Map<String, Object> requestBody);

	@DeleteMapping("/catalog/delete")
	public <T> ResponseEntity<T> deleteDetails(int productId);
}
