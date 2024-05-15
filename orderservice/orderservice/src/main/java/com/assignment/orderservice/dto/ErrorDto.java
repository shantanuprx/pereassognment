package com.assignment.orderservice.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Generic error DTO
 */
@Data
@AllArgsConstructor
public class ErrorDto {

	private HttpStatus status;

	private String message;

	private String trace;

	private long timestamp;

}
