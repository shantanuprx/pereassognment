package com.assignment.ecommerceweb.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.ecommerceweb.adapters.UserServicesAdapter;
import com.assignment.ecommerceweb.constants.GatewayServiceConstants;
import com.assignment.ecommerceweb.dto.AuthenticationRequestDto;
import com.assignment.ecommerceweb.dto.RegistrationDto;
import com.assignment.ecommerceweb.dto.ResponseDto;
import com.assignment.ecommerceweb.entity.RedisUserEntity;
import com.assignment.ecommerceweb.entity.User;
import com.assignment.ecommerceweb.repository.RedisUserRepository;
import com.assignment.ecommerceweb.repository.UserRepository;

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
	private RedisUserRepository redisUserRepository;

	public ResponseEntity<T> createNewUser(RegistrationDto registrationDto) {
		log.info("Entering createNewUser method at  {} ", System.currentTimeMillis());
		try {
			User userEntity = UserServicesAdapter.convertModelToEntity(registrationDto);
			userRepository.save(userEntity);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body((T) new ResponseDto(HttpStatus.CREATED, GatewayServiceConstants.RECORD_CREATION_MESSAGE));
		} catch (Exception ex) {
			log.error("Exception occurred in createNewUser method with exception ", ex);
			return null;
		} finally {
			log.info("Exiting createNewUser method at  {} ", System.currentTimeMillis());
		}

	}

	public String generateToken(String username) {
		return authTokenService.generateToken(username);
	}

	public Claims validateToken(String token) {
		return authTokenService.validateToken(token);
	}

	public void saveUserSession(AuthenticationRequestDto authenticationRequestDto, String jwtToken) {
		Optional<User> userEntity = userRepository.findByEmail(authenticationRequestDto.getEmailId());
		RedisUserEntity redisUserEntity = new RedisUserEntity(jwtToken, userEntity.get().getUserId(), userEntity.get().getUserRole());
		redisUserRepository.save(redisUserEntity);
	}
}