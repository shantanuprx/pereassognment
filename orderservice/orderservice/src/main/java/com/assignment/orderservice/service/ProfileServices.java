package com.assignment.orderservice.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.ResponseDto;
import com.assignment.orderservice.entity.User;
import com.assignment.orderservice.exception.BadRequestException;
import com.assignment.orderservice.repository.UserRepository;
import com.assignment.orderservice.util.ResponseUtil;

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
			throw new BadRequestException(OrdersConstant.USER_ID_NOT_EXIST);
		}
		return loggedInEntity.get();
	}

}
