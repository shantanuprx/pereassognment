package com.assignment.apispecservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class to map authentication request, in order to validate user credentials
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {

	@NotEmpty
	@Schema(example = "200 OK")
	private String staus;

	@NotEmpty
	@Schema(example = "eyJhbGciOiJIUzI1NiJ9.eyJsb2dnZWRJblVzZXJJZCI6NywidXNlclJvbGUiOiJDVVNUT01FUiIsInN1YiI6Imt1bWFyOTM3ODJAZ21haWwuY29tIiwiaWF0IjoxNzE2MTI0ODE2LCJleHAiOjE3MTYxMjY2MTZ9.ya1sYCsOtKVvS22ahOyXQv_RpgOLlYzroJsq47Yx3zQ")
	private String token;

}