package com.assignment.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class AddressDto extends BaseDto {

	private Integer recordId;
	
	@NotBlank
	@Size(max = 255)
	private String addressLine1;

	private String addressLine2;

	@NotBlank
	@Size(max = 100)
	private String city;

	@NotBlank
	@Size(max = 100)
	private String state;

	@NotBlank
	@Size(max = 20)
	private String pincode;

}
