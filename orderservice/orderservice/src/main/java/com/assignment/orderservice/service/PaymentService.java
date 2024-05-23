package com.assignment.orderservice.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.assignment.orderservice.constants.GatewayServiceConstants;
import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.dto.ValidationDto;
import com.assignment.orderservice.feignclients.PaymentServiceFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class PaymentService {

	@Autowired
	private PaymentServiceFeignClient paymentServiceFeignClient;

	private static final Set<String> ALLOWED_PAYMENT_SOURCE = new HashSet<>(Arrays.asList("card"));

	/**
	 * Service method to call validation service in order to validate payment
	 * 
	 * @param ordersDto
	 * @return
	 * @throws Exception
	 */
	public String validatePayment(OrdersDto ordersDto) throws Exception {
		log.debug("Entering validatePayment method at {}", System.currentTimeMillis());
		try {
			if (ALLOWED_PAYMENT_SOURCE.contains(ordersDto.getPaymentSource())) {
				Map<String, Object> requestMap = new HashMap<>();
				requestMap.put(GatewayServiceConstants.TOKEN, ordersDto.getToken());
				requestMap.put("recordId", ordersDto.getPaymentId());
				requestMap.put("paymentType", ordersDto.getPaymentSource());
				ResponseEntity<Object> response = paymentServiceFeignClient.validateDetails("payment", requestMap);
				ValidationDto validationDto = new ObjectMapper().convertValue(response.getBody(), ValidationDto.class);
				System.out.println(validationDto);
				if (validationDto.getValidity()) {
					return null;
				} else {
					return validationDto.getErrorMessage();
				}
			} else {
				return OrdersConstant.PAYMENT_TYPE_NOT_ALLOWED;
			}
		} catch (Exception ex) {
			log.debug("Exception occurred while validating payment details with exception", ex);
			throw new Exception(ex);
		} finally {
			log.debug("Exiting validatePayment method at {}", System.currentTimeMillis());
		}
	}

}
