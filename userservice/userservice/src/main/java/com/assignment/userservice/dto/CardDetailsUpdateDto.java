package com.assignment.userservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailsUpdateDto extends BaseDto{

	@NotNull
	private Integer recordId;
	
	@Size(min = 16, max = 16, message = "Card number should be of 16 digits")
    private String cardNumber;

	@Future(message = "Please enter valid expiry")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
    private Date expiryDate;

	@Size(max = 100)
    private String cardHolderName;

}
