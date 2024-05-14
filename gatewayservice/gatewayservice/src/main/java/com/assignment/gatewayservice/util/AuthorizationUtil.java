package com.assignment.gatewayservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.gatewayservice.entity.RedisUserEntity;
import com.assignment.gatewayservice.entity.User;
import com.assignment.gatewayservice.repository.RedisUserRepository;
import com.assignment.gatewayservice.repository.UserRepository;
import com.assignment.gatewayservice.service.AuthorizationService;

import io.jsonwebtoken.Claims;

@Component
public class AuthorizationUtil<T> {

	@Autowired
	private RedisUserRepository redisUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorizationService<T> authorizationService;
	
	public String createSession(String token) {
		Claims tokenPayload = authorizationService.validateToken(token);
		String emailId = (String) tokenPayload.get("sub");
		User userEntity = userRepository.findByEmail(emailId).get();
		RedisUserEntity redisUserEntity = new RedisUserEntity(HashUtil.generateRandomId(), userEntity.getUserId(), userEntity.getUserRole());
		redisUserRepository.save(redisUserEntity);
		return redisUserEntity.getSessionId();
	}

}
