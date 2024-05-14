package com.assignment.ecommerceweb.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

	private HttpStatus status;
	
	private String message;
}
