package com.assignment.userservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(value = Include.NON_NULL)
public class CardDetailsDto extends BaseDto{

	private int recordId;
	
    private String cardNumber;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
    private Date expiryDate;

    private String cardHolderName;

}
