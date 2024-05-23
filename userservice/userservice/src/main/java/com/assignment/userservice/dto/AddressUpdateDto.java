package com.assignment.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(value = Include.NON_NULL)
public class AddressUpdateDto extends BaseDto {

	@NotNull
	private Integer recordId;

	@Size(max = 255)
    private String addressLine1;

	@Size(max = 255)
    private String addressLine2;

	@Size(max = 100)
    private String city;

	@Size(max = 100)
    private String state;

	@Size(max = 20)
    private String pincode;

}