package com.assignment.ecommerceweb.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.ecommerceweb.dto.ResponseDto;
import com.assignment.ecommerceweb.util.ResponseUtil;

@SuppressWarnings("unchecked")
@Service("profile")
public class ProfileServices<T> implements BaseService<T> {

	private static final String OPERATION_NOT_SUPPORTED = "Operation Not Supported";
	@Autowired
	private ResponseUtil<T> responseUtil;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		return responseUtil.prepareResponse((T) new ResponseDto(HttpStatus.BAD_REQUEST, OPERATION_NOT_SUPPORTED),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		return responseUtil.prepareResponse((T) new ResponseDto(HttpStatus.BAD_REQUEST, OPERATION_NOT_SUPPORTED),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		return responseUtil.prepareResponse((T) new ResponseDto(HttpStatus.BAD_REQUEST, OPERATION_NOT_SUPPORTED),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		return responseUtil.prepareResponse((T) new ResponseDto(HttpStatus.BAD_REQUEST, OPERATION_NOT_SUPPORTED),
				HttpStatus.BAD_REQUEST);
	}

}
