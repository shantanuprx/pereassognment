package com.assignment.gatewayservice.exception;

/**
 * Custom runtime exception class. Throw when requested service is not available.
 */
public class ServiceBeanException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4435370184664300230L;

	public ServiceBeanException() {
		super();
	}

	public ServiceBeanException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceBeanException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceBeanException(String message) {
		super(message);
	}

	public ServiceBeanException(Throwable cause) {
		super(cause);
	}
}
