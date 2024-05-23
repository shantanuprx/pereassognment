package com.assignment.apispecservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/* *
 * Address DTO for handling inbound and outbound data.
 * */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class AddressDto {

	@Schema(example = "1")
	private Integer recordId;
	
	@NotBlank
	@Size(max = 255)
	@Schema(example = "38 A, Jalmahal")
	private String addressLine1;

	@Schema(example = "Amer road")
	private String addressLine2;

	@NotBlank
	@Size(max = 100)
	@Schema(example = "Jaipur")
	private String city;

	@NotBlank
	@Size(max = 100)
	@Schema(example = "Rajasthan")
	private String state;

	@NotBlank
	@Size(max = 20)
	@Schema(example = "302002")
	private String pincode;
	
	@NotBlank
	@Schema(example = "JWT Token from auth service")
	private String token;

}
