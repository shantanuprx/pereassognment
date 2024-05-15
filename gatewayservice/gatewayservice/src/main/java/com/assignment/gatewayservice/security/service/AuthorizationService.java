package com.assignment.gatewayservice.security.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.gatewayservice.adapter.UserServicesAdapter;
import com.assignment.gatewayservice.constants.GatewayServiceConstants;
import com.assignment.gatewayservice.dto.RegistrationDto;
import com.assignment.gatewayservice.dto.ResponseDto;
import com.assignment.gatewayservice.entity.User;
import com.assignment.gatewayservice.exception.UserAlreadyRegisteredException;
import com.assignment.gatewayservice.repository.UserRepository;
import com.assignment.gatewayservice.util.JedisClientHelper;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unchecked")
@Service
@Slf4j
public class AuthorizationService<T> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthTokenService authTokenService;
	
	@Autowired
	private JedisClientHelper jedisClientHelper;

	public ResponseEntity<T> createNewUser(RegistrationDto registrationDto) throws Exception {
		log.info("Entering createNewUser method at  {} ", System.currentTimeMillis());
		try {
			if(userRepository.existsByEmail(registrationDto.getEmailId())) {
				 throw new UserAlreadyRegisteredException("Email id already registered ");
			}
			User userEntity = UserServicesAdapter.convertModelToEntity(registrationDto);
			userRepository.save(userEntity);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body((T) new ResponseDto(userEntity.getUserId(), HttpStatus.CREATED, GatewayServiceConstants.RECORD_CREATION_MESSAGE));
		} catch (Exception ex) {
			log.error("Exception occurred in createNewUser method with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting createNewUser method at  {} ", System.currentTimeMillis());
		}

	}

	public String generateToken(String userName) {
		Optional<User> userEntity = userRepository.findByEmail(userName);
		return authTokenService.generateToken(userEntity.get());
	}

	public Claims validateToken(Map<String, Object> requestMap) {
		Claims tokenClaims = authTokenService.validateToken(requestMap.get("token").toString());
		requestMap.put(GatewayServiceConstants.LOGGED_IN_USER_ID, tokenClaims.get(GatewayServiceConstants.LOGGED_IN_USER_ID));
		requestMap.put(GatewayServiceConstants.USER_ROLE, tokenClaims.get(GatewayServiceConstants.USER_ROLE));
		return tokenClaims;
	}

	public void saveUserSession(String jwtToken) {
		jedisClientHelper.saveKeyPair(jwtToken, String.valueOf(true));
	}
}
