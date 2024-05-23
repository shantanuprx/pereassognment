package com.assignment.orderservice.feignclients.fallbacks;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.assignment.orderservice.dto.ValidationDto;
import com.assignment.orderservice.feignclients.PaymentServiceFeignClient;

@Component
public class PaymentServiceFeignClientFallBack implements PaymentServiceFeignClient{

	@Override
	public ResponseEntity<Object> validateDetails(String serviceName, Map<String, Object> requestMap) throws Exception {
		ValidationDto validationDto = new ValidationDto();
		validationDto.setValidity(false);
		validationDto.setErrorMessage("Payment details cannot be verified right now");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(validationDto);
	}
	
}
