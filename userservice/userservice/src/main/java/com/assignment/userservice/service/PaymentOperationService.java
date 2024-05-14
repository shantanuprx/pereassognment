package com.assignment.userservice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.userservice.constants.PaymentConstants;
import com.assignment.userservice.exception.BadRequestException;
import com.assignment.userservice.util.ServiceLocator;

@Service("payment")
public class PaymentOperationService<T> implements BaseService<T> {

	@Autowired
	private ServiceLocator<T> serviceLocator;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		return serviceLocator.locateServiceBean(fetchPaymentType(requestData)).getDetails(requestData);
	}

	@Override
	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		return serviceLocator.locateServiceBean(fetchPaymentType(requestData)).addDetails(requestData);
	}

	@Override
	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		return serviceLocator.locateServiceBean(fetchPaymentType(requestData)).updateDetails(requestData);
	}

	@Override
	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		return serviceLocator.locateServiceBean(fetchPaymentType(requestData)).deleteDetails(requestData);
	}

	private String fetchPaymentType(Map<String, Object> requestData) {
		if (requestData.containsKey(PaymentConstants.PAYMENT_TYPE)) {
			String payementType = requestData.get(PaymentConstants.PAYMENT_TYPE).toString();
			requestData.remove(PaymentConstants.PAYMENT_TYPE);
			return payementType;
		} else {
			throw new BadRequestException("Payment Type is required");
		}
	}
}
