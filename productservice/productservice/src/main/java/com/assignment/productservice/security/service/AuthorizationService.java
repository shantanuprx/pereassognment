package com.assignment.productservice.security.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.productservice.constants.GatewayServiceConstants;
import com.assignment.productservice.util.JedisClientHelper;

import io.jsonwebtoken.Claims;

@Service
public class AuthorizationService<T> {

	@Autowired
	private AuthTokenService authTokenService;
	
	@Autowired
	private JedisClientHelper jedisClientHelper;

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
