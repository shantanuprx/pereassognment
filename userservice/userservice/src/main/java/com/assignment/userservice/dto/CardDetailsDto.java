package com.assignment.userservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class CardDetailsDto extends BaseDto {

	private int recordId;

	@NotNull
	@Size(min = 16, max = 16)
	private String cardNumber;

	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
	private Date expiryDate;

	@NotNull
	@Size(max = 100)
	private String cardHolderName;

}
