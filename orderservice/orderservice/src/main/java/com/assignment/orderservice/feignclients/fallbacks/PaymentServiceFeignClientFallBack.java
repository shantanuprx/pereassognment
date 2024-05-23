package com.assignment.orderservice.feignclients.fallbacks;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import com.assignment.orderservice.dto.ValidationDto;
import com.assignment.orderservice.feignclients.PaymentServiceFeignClient;

@Component
public class PaymentServiceFeignClientFallBack implements PaymentServiceFeignClient{

	@Override
	public ResponseEntity<Object> validateDetails(Map<String, Object> requestMap, Map<String, String> headers, String serviceName) throws Exception {
		ValidationDto validationDto = new ValidationDto();
		validationDto.setValidity(false);
		validationDto.setErrorMessage("Payment details cannot be verified right now");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(validationDto);
	}
	
}
