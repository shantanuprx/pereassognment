package com.assignment.gatewayservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.assignment.gatewayservice.feignclients.BaseClient;
import com.assignment.productservice.exception.ServiceBeanException;

@Component
public class ServiceLocator<T> {

	@Autowired
	private ApplicationContext applicationContext;

	public BaseClient<T> locateServiceBean(String serviceName) {
		try {
			@SuppressWarnings("unchecked")
			BaseClient<T> baseServiceBean = (BaseClient<T>) applicationContext.getBean(serviceName);
			return baseServiceBean;
		} catch (Exception ex) {
			throw new ServiceBeanException(ex);
		}
	}
}
