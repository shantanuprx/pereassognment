package com.assignment.orderservice.feignclients.fallbacks;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.assignment.orderservice.dto.ValidationDto;
import com.assignment.orderservice.feignclients.ProductServiceFeignClient;

@Component
public class ProductServiceFeignClientFallBack implements ProductServiceFeignClient {

	@Override
	public ResponseEntity<Object> validateDetails(Map<String, Object> requestMap, Map<String, String> requestHeaders,
			String serviceName) throws Exception {
		ValidationDto validationDto = new ValidationDto();
		validationDto.setValidity(false);
		validationDto.setErrorMessage("Product details cannot be verified right now");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(validationDto);
	}

}
