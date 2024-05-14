package com.assignment.productservice.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

	private int id;
	
	private HttpStatus status;
	
	private String message;
}
