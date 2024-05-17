package com.assignment.orderservice.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.assignment.orderservice.constants.GatewayServiceConstants;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.dto.ValidationDto;
import com.assignment.orderservice.feignclients.ProductServiceFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProductService {

	@Autowired
	private ProductServiceFeignClient productServiceFeignClient;
	

	
	public String validateProduct(OrdersDto ordersDto) throws Exception {
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
	}
}
