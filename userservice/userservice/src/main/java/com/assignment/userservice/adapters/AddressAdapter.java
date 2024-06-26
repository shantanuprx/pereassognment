package com.assignment.userservice.adapters;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.assignment.userservice.dto.AddressDto;
import com.assignment.userservice.dto.AddressUpdateDto;
import com.assignment.userservice.entity.Address;
import com.assignment.userservice.entity.User;

import jakarta.validation.Valid;

@Component
@Validated
public class AddressAdapter {

	/**
	 * Maps entity to model
	 * 
	 * @param addressEntity
	 * @return new AddressDto
	 */
	
	public AddressDto convertEntityToModel(Address addressEntity) {
		AddressDto addressDto = new AddressDto();
		addressDto.setRecordId(addressEntity.getRecordId());
		addressDto.setAddressLine1(addressEntity.getAddressLine1());
		addressDto.setAddressLine2(addressEntity.getAddressLine2());
		addressDto.setCity(addressEntity.getCity());
		addressDto.setPincode(addressEntity.getPincode());
		addressDto.setState(addressEntity.getState());
		return addressDto;
	}

	/**
	 * Maps Dto to entity
	 * 
	 * @param addressDto
	 * @param user
	 * @return new Address entity
	 */
	
	public Address convertModelToEntityForInsertion(@Valid AddressDto addressDto, User user) {
		Address addressEntity = new Address();
		addressEntity.setAddressLine1(addressDto.getAddressLine1());
		addressEntity.setAddressLine2(addressDto.getAddressLine2());
		addressEntity.setCity(addressDto.getCity());
		addressEntity.setPincode(addressDto.getPincode());
		addressEntity.setState(addressDto.getState());
		addressEntity.setUser(user);
		addressEntity.setCreatedBy(user.getUserId());
		addressEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		return addressEntity;
	}

	/**
	 * Update all the eligible values for the update 
	 * 
	 * @param addressEntity
	 * @param addressDto
	 */
	public void convertModelToEntityForUpdate(Address addressEntity, AddressUpdateDto addressDto) {
		if (addressDto.getAddressLine1() != null) {
			addressEntity.setAddressLine1(addressDto.getAddressLine1());
		}

		if (addressDto.getAddressLine2() != null) {
			addressEntity.setAddressLine2(addressDto.getAddressLine2());
		}

		if (addressDto.getCity() != null) {
			addressEntity.setCity(addressDto.getCity());
		}

		if (addressDto.getPincode() != null) {
			addressEntity.setPincode(addressDto.getPincode());
		}

		if (addressDto.getState() != null) {
			addressEntity.setState(addressDto.getState());
		}
		
		addressEntity.setUpdatedBy(addressDto.getLoggedInUserId());
		addressEntity.setUpdatedDate(Timestamp.valueOf(LocalDateTime.now()));
	}

}
