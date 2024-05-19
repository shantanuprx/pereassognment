package com.assignment.orderservice.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.assignment.orderservice.constants.GatewayServiceConstants;
import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.dto.ValidationDto;
import com.assignment.orderservice.feignclients.ProductServiceFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductService {

	@Autowired
	private ProductServiceFeignClient productServiceFeignClient;
	
	/**
	 * Service method to call validation service in order to validate product
	 * @param ordersDto
	 * @return
	 * @throws Exception
	 */
	public String validateProduct(OrdersDto ordersDto) throws Exception {
		log.debug("Entering validateProduct method at {}", System.currentTimeMillis());
		try {
			Map<String, Object> requestMap = new HashMap<>();
			requestMap.put(GatewayServiceConstants.TOKEN, ordersDto.getToken());
			requestMap.put("productId", ordersDto.getProductId());
			ResponseEntity<Object> response = productServiceFeignClient.validateDetails("product", requestMap);
			ValidationDto validationDto = new ObjectMapper().convertValue(response.getBody(), ValidationDto.class);
			System.out.println(validationDto);
			if (validationDto.getValidity()) {
				return null;
			} else {
				return validationDto.getErrorMessage();
			}
		} catch (Exception ex) {
			log.debug("Exception occurred while validating address details with exception", ex);
			throw new Exception(ex);
		} finally {
			log.debug("Exiting validateProduct method at {}", System.currentTimeMillis());
		}
	}
}
