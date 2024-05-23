package com.assignment.apispecservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CardDetailsDto {

	@Schema(example = "1")
	private int recordId;

	@NotNull
	@Size(min = 16, max = 16, message = "Card number should be of 16 digits")
	@Schema(example = "4567456745674567")
	private String cardNumber;

	@Future(message = "Please enter valid expiry")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
	@Schema(description = "DD/MM/YYYY", example = "23/12/2023")
	private Date expiryDate;

	@NotNull
	@Size(max = 100)
	@Schema(example = "Shantanu Kumar")
	private String cardHolderName;

	@NotBlank
	@Schema(example = "JWT Token from auth service")
	private String token;

	@NotBlank
	@Schema(example = "card", allowableValues = { "card" })
	private String paymentType;
}
