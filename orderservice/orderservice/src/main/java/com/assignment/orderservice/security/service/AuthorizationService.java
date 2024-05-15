package com.assignment.orderservice.security.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.orderservice.constants.GatewayServiceConstants;

import io.jsonwebtoken.Claims;

@Service
public class AuthorizationService<T> {

	@Autowired
	private AuthTokenService authTokenService;

	public Claims validateToken(Map<String, Object> requestMap) {
		Claims tokenClaims = authTokenService.validateToken(requestMap.get("token").toString());
		requestMap.put(GatewayServiceConstants.LOGGED_IN_USER_ID, tokenClaims.get(GatewayServiceConstants.LOGGED_IN_USER_ID));
		requestMap.put(GatewayServiceConstants.USER_ROLE, tokenClaims.get(GatewayServiceConstants.USER_ROLE));
		return tokenClaims;
	}
}
