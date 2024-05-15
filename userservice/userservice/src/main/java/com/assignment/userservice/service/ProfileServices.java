package com.assignment.userservice.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.userservice.constants.AddressConstants;
import com.assignment.userservice.dto.ResponseDto;
import com.assignment.userservice.entity.User;
import com.assignment.userservice.exception.BadRequestException;
import com.assignment.userservice.repository.UserRepository;
import com.assignment.userservice.util.ResponseUtil;

/* *
 * Service class implementation for the profile related services
 * getDetails -  Operation Not Supported
 * addDetails -  Operation Not Supported
 * updateDetails - Operation Not Supported
 * deleteDetails - Operation Not Supported
 * */
@SuppressWarnings("unchecked")
@Service("profile")
public class ProfileServices<T> implements BaseService<T> {

	private static final String OPERATION_NOT_SUPPORTED = "Operation Not Supported";
	
	@Autowired
	private ResponseUtil<T> responseUtil;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		return responseUtil.prepareResponse((T) new ResponseDto(0, HttpStatus.BAD_REQUEST, OPERATION_NOT_SUPPORTED),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		return responseUtil.prepareResponse((T) new ResponseDto(0, HttpStatus.BAD_REQUEST, OPERATION_NOT_SUPPORTED),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		return responseUtil.prepareResponse((T) new ResponseDto(0, HttpStatus.BAD_REQUEST, OPERATION_NOT_SUPPORTED),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		return responseUtil.prepareResponse((T) new ResponseDto(0, HttpStatus.BAD_REQUEST, OPERATION_NOT_SUPPORTED),
				HttpStatus.BAD_REQUEST);
	}

	public User fetchUserEntity(int loggedInUserId) {
		Optional<User> loggedInEntity = userRepository.findById(loggedInUserId);
		if (loggedInEntity.isEmpty()) {
			throw new BadRequestException(AddressConstants.USER_ID_NOT_EXIST);
		}
		return loggedInEntity.get();
	}

}
