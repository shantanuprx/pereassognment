package com.assignment.ecommerceweb.dto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CardDetailsDto extends BaseDto{

	private int recordId;
	
    private int userId;

    private String cardNumber;

    private Date expiryDate;

    private String cardHolderName;

    private Timestamp createdDate;

    private String createdBy;

    private Timestamp updatedDate;

    private String updatedBy;
}
