package com.assignment.orderservice.feignclients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.ValidationDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
@FeignClient(name = "productservice")
public interface ProductServiceFeignClient {

	@GetMapping("/{serviceName}/validate")
//	@CircuitBreaker(name = "circuitBreaker", fallbackMethod = "validateDetailsFallBack")
	public ResponseEntity<Object> validateDetails(@PathVariable("serviceName") String serviceName,
			@RequestBody Map<String, Object> requestMap) throws Exception;

//	default ResponseEntity<Object> validateDetailsFallBack(@PathVariable("serviceName") String serviceName,
//			@RequestBody Map<String, Object> requestMap) throws Exception {
//		ValidationDto validationDto = new ValidationDto(false, OrdersConstant.SERVICE_UNAVAILABLE);
//		return ResponseEntity.status(HttpStatus.OK).body(validationDto);
//	}
}
