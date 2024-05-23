package com.assignment.orderservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.assignment.orderservice.constants.GatewayServiceConstants;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.dto.ValidationDto;
import com.assignment.orderservice.feignclients.AddressServiceFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class AddressService {

	@Autowired
	private AddressServiceFeignClient addressServiceFeignClient;

	/**
	 * Service method to call validation service in order to validate address
	 * 
	 * @param ordersDto
	 * @return
	 * @throws Exception
	 */
	public String validateAddress(OrdersDto ordersDto) throws Exception {
		log.debug("Entering validateAddress method at {}", System.currentTimeMillis());
		try {
			Map<String, Object> requestMap = new HashMap<>();
			requestMap.put(GatewayServiceConstants.TOKEN, ordersDto.getToken());
			requestMap.put("recordId", ordersDto.getAddressId());
			ResponseEntity<Object> response = addressServiceFeignClient.validateDetails("address", requestMap);
			ValidationDto validationDto = new ObjectMapper().convertValue(response.getBody(), ValidationDto.class);
			if (validationDto.getValidity()) {
				return null;
			} else {
				return validationDto.getErrorMessage();
			}
		} catch (Exception ex) {
			log.debug("Exception occurred while validating address details with exception", ex);
			throw new Exception(ex);
		} finally {
			log.debug("Exiting validateAddress method at {}", System.currentTimeMillis());
		}
	}
}
