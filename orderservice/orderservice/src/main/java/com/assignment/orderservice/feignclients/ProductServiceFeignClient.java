package com.assignment.orderservice.feignclients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.assignment.orderservice.feignclients.fallbacks.ProductServiceFeignClientFallBack;

@FeignClient(name = "productservice", fallback = ProductServiceFeignClientFallBack.class)
public interface ProductServiceFeignClient {

	@GetMapping("/{serviceName}/validate")
	public ResponseEntity<Object> validateDetails(@PathVariable("serviceName") String serviceName,
			@RequestBody Map<String, Object> requestMap) throws Exception;
}
