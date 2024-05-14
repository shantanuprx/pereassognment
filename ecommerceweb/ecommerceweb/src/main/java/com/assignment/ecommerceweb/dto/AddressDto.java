package com.assignment.ecommerceweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AddressDto extends BaseDto{

	private int id;

    private int userId;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String pincode;

}
