package com.assignment.ecommerceweb.adapters;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.assignment.ecommerceweb.constants.GatewayServiceConstants;
import com.assignment.ecommerceweb.dto.RegistrationDto;
import com.assignment.ecommerceweb.entity.User;
import com.assignment.ecommerceweb.util.HashUtil;

public class UserServicesAdapter {

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
		return entity;
	}

}
