package com.assignment.productservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.assignment.productservice.exception.ServiceBeanException;
import com.assignment.productservice.service.BaseService;

@Component
public class ServiceLocator<T> {

	@Autowired
	private ApplicationContext applicationContext;

	public BaseService<T> locateServiceBean(String serviceName) {
		try {
			@SuppressWarnings("unchecked")
			BaseService<T> baseServiceBean = (BaseService<T>) applicationContext.getBean(serviceName);
			return baseServiceBean;
		} catch (Exception ex) {
			throw new ServiceBeanException(ex);
		}
	}
}
