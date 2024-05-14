package com.assignment.userservice.dto.filters;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.assignment.userservice.dto.AddressDto;

import jakarta.validation.Valid;

@Validated
@Component
public class AddressFilters {

	public void validateAddressDto(@Valid AddressDto addressDto) {
		return;
	}
}
