package com.assignment.gatewayservice.adapter;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.assignment.gatewayservice.constants.GatewayServiceConstants;
import com.assignment.gatewayservice.dto.RegistrationDto;
import com.assignment.gatewayservice.entity.User;
import com.assignment.gatewayservice.util.HashUtil;

public class UserServicesAdapter {

	/**
	 * Converts model to entity for user registration
	 * 
	 * @param registrationDto
	 * @return new User entity for registration
	 * @throws NoSuchAlgorithmException
	 */
	public static User convertModelToEntity(RegistrationDto registrationDto) throws NoSuchAlgorithmException {
		User entity = new User();
		entity.setFirstName(registrationDto.getFirstName());
		entity.setMidName(registrationDto.getMidName());
		entity.setLastName(registrationDto.getLastName());
		entity.setEmail(registrationDto.getEmailId());
		entity.setPassword(HashUtil.generateMd5Hash(registrationDto.getPassword()));
		entity.setCreatedBy(GatewayServiceConstants.GATEWAY_SERVICE);
		entity.setCreatedDate(new Date());
		entity.setDateOfBirth(registrationDto.getDateOfBirth());
		entity.setMobileNumber(registrationDto.getMobileNumber());
		entity.setStatus(GatewayServiceConstants.ACTIVE_FLAG);
		entity.setUserRole(GatewayServiceConstants.CUSTOMER);
		return entity;
	}

}
