package com.assignment.userservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
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
	
    private int userId;

    private String cardNumber;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
    private Date expiryDate;

    private String cardHolderName;

}
