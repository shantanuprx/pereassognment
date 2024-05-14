package com.assignment.userservice.exception;

public class DataParsingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5533213668496798346L;

	public DataParsingException() {
		super();
	}

	public DataParsingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataParsingException(String message) {
		super(message);
	}

	public DataParsingException(Throwable cause) {
		super(cause);
	}

}
