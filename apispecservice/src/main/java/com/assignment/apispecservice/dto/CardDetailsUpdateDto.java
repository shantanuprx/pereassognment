package com.assignment.apispecservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailsUpdateDto extends BaseDto{

	@NotNull
	@Schema(example = "1")
	private Integer recordId;
	
	@Schema(example = "4567456745674567")
	@Size(min = 16, max = 16, message = "Card number should be of 16 digits")
    private String cardNumber;

	@Future(message = "Please enter valid expiry")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
	@Schema(description = "DD/MM/YYYY", example = "23/12/2023")
    private Date expiryDate;

	@Schema(example = "Shantanu Kumar")
	@Size(max = 100)
    private String cardHolderName;

    @NotBlank
	@Schema(example = "JWT Token from auth service")
	private String token;
    
    @NotBlank
  	@Schema(example = "card", allowableValues = {"card"})
    private String paymentType;
}
