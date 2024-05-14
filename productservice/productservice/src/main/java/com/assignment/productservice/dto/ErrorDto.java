package com.assignment.productservice.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {

	private HttpStatus status;

	private String message;

	private String trace;

	private long timestamp;

}
