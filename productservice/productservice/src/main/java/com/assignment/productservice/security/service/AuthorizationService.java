package com.assignment.productservice.security.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.productservice.constants.GatewayServiceConstants;

import io.jsonwebtoken.Claims;

@Service
public class AuthorizationService<T> {

	@Autowired
	private AuthTokenService authTokenService;

	/* *
	 * Function responsible for setting logged in user id and user role based on the payload passed in JWT token
	 * */
	public Claims validateToken(Map<String, Object> requestMap) {
		Claims tokenClaims = authTokenService.validateToken(requestMap.get(GatewayServiceConstants.TOKEN).toString());
		requestMap.put(GatewayServiceConstants.LOGGED_IN_USER_ID, tokenClaims.get(GatewayServiceConstants.LOGGED_IN_USER_ID));
		requestMap.put(GatewayServiceConstants.USER_ROLE, tokenClaims.get(GatewayServiceConstants.USER_ROLE));
		return tokenClaims;
	}

}
