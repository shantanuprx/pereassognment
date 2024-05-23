package com.assignment.apispecservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* *
 * Card Details DTO for handling inbound and outbound data.
 * */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class CardDetailsFetchDto {

	@Schema(example = "1")
	@Min(1)
	@NotNull
	private Integer recordId;

	@NotBlank
	@Schema(example = "JWT Token from auth service")
	private String token;
	
	@NotBlank
	@Schema(example = "card", allowableValues = { "card" })
	private String paymentType;
}
