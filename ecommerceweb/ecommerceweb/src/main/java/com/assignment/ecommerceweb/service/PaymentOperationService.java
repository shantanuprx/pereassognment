package com.assignment.ecommerceweb.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.ecommerceweb.constants.PaymentConstants;
import com.assignment.ecommerceweb.util.ServiceLocator;

@Service("payment")
public class PaymentOperationService<T> implements BaseService<T> {

	@Autowired
	private ServiceLocator<T> serviceLocator;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		return serviceLocator.locateServiceBean(requestData.get(PaymentConstants.PAYMENT_TYPE).toString())
				.getDetails(requestData);
	}

	@Override
	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		return serviceLocator.locateServiceBean(requestData.get(PaymentConstants.PAYMENT_TYPE).toString())
				.addDetails(requestData);
	}

	@Override
	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		return serviceLocator.locateServiceBean(requestData.get(PaymentConstants.PAYMENT_TYPE).toString())
				.updateDetails(requestData);
	}

	@Override
	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		return serviceLocator.locateServiceBean(requestData.get(PaymentConstants.PAYMENT_TYPE).toString())
				.deleteDetails(requestData);
	}

}
