package com.assignment.apispecservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
public class AuthenticationRequestDto {

	@NotEmpty
	@Email
	@Schema(example = "kumar937@gmail.com")
	private String emailId;

	@NotEmpty
	@Schema(example = "Oracle@123")
	private String password;

}