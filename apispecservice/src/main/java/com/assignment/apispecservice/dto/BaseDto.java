package com.assignment.apispecservice.dto;

import jakarta.validation.constraints.NotBlank;

public class BaseDto {

	@NotBlank
	private String token;
}
