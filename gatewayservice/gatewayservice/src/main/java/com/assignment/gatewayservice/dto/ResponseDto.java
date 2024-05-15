package com.assignment.gatewayservice.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Generic Response DTO class
 */
@Data
@AllArgsConstructor
public class ResponseDto {

	private int id;
	
	private HttpStatus status;
	
	private String message;
}
