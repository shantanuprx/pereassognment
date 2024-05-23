package com.assignment.apispecservice.dto;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Generic Response DTO class
 */
@Data
@AllArgsConstructor
public class ResponseDto {

	@Schema(example = "1", description = "Unique request Id")
	private int id;
	
	@Schema(example = "200 OK", description = "Http status")
	private HttpStatus status;
	
	@Schema(example = "<Entity> added/updated/deleted successfully")
	private String message;
}
