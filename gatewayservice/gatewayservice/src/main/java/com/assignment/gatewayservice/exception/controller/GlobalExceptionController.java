package com.assignment.gatewayservice.exception.controller;

import java.sql.SQLException;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.gatewayservice.dto.ErrorDto;
import com.assignment.gatewayservice.exception.BadRequestException;
import com.assignment.gatewayservice.exception.DataParsingException;
import com.assignment.gatewayservice.exception.ServiceBeanException;
import com.assignment.gatewayservice.exception.UserAlreadyRegisteredException;

import jakarta.validation.ConstraintViolationException;


/* *
 * Central Exception handler. It will handle all the exception thrown from the whole application
 * except JVM error. Please check handleException() method in this class
 * **/
@SuppressWarnings("unchecked")
@ControllerAdvice
public class GlobalExceptionController<T> {

	@ExceptionHandler(GenericJDBCException.class)
	public ResponseEntity<T> handleDatabaseException(GenericJDBCException genericJDBCException) {
		if (genericJDBCException.getCause() instanceof SQLException
				&& ((SQLException) genericJDBCException.getCause()).getSQLState().equalsIgnoreCase("45000")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((T) new ErrorDto(HttpStatus.BAD_REQUEST,
					genericJDBCException.getCause().getMessage(), null, System.currentTimeMillis()));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body((T) new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, genericJDBCException.getMessage(),
							genericJDBCException.getLocalizedMessage(), System.currentTimeMillis()));
		}
	}

	@ExceptionHandler(ServiceBeanException.class)
	public ResponseEntity<T> handleInvalidServiceException(ServiceBeanException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body((T) new ErrorDto(HttpStatus.NOT_FOUND,
				"Invalid Service name or URL", "Invalid Service name or URL", System.currentTimeMillis()));
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<T> handleBadRequestException(BadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((T) new ErrorDto(HttpStatus.BAD_REQUEST,
				ex.getMessage(), "Invalid request payload", System.currentTimeMillis()));
	}

	@ExceptionHandler(UserAlreadyRegisteredException.class)
	public ResponseEntity<T> handleAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((T) new ErrorDto(HttpStatus.BAD_REQUEST,
				ex.getMessage(), ex.getLocalizedMessage(), System.currentTimeMillis()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<T> handleNotValidException(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				(T) new ErrorDto(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getMessage(), System.currentTimeMillis()));
	}

	@ExceptionHandler(DataParsingException.class)
	public ResponseEntity<T> handleNotValidException(DataParsingException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				(T) new ErrorDto(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getMessage(), System.currentTimeMillis()));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<T> handleNotValidException(ConstraintViolationException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				(T) new ErrorDto(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getMessage(), System.currentTimeMillis()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<T> handleException(Exception ex) {
		if (ex instanceof BadRequestException) {
			return handleBadRequestException((BadRequestException) ex);

		} else if (ex instanceof ConstraintViolationException) {
			return handleNotValidException((ConstraintViolationException) ex);

		} else if (ex instanceof GenericJDBCException) {
			return handleDatabaseException((GenericJDBCException) ex);

		} else if (ex instanceof ServiceBeanException) {
			return handleInvalidServiceException((ServiceBeanException) ex);

		} else if (ex instanceof UserAlreadyRegisteredException) {
			return handleAlreadyRegisteredException((UserAlreadyRegisteredException) ex);

		} else if (ex instanceof MethodArgumentNotValidException) {
			return handleNotValidException((MethodArgumentNotValidException) ex);

		} else if (ex instanceof DataParsingException) {
			return handleNotValidException((DataParsingException) ex);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body((T) new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getLocalizedMessage(),
							System.currentTimeMillis()));
		}

	}
}
