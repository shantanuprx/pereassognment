package com.assignment.apispecservice.dto;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Generic error DTO
 */
@Data
@AllArgsConstructor
public class ErrorDto {

	@Schema(example = "400 Bad Request")
	private HttpStatus status;

	@Schema(example = "Not eligible")
	private String message;

	@Schema(example = "Must not be blank")
	private String trace;

	@Schema(example = "678200000")
	private long timestamp;

}
