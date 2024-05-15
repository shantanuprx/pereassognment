package com.assignment.gatewayservice.dto;

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
	private String emailId;

	@NotEmpty
	private String password;

}