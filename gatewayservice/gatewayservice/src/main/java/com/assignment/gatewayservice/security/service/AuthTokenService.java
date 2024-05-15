package com.assignment.gatewayservice.security.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.assignment.gatewayservice.constants.GatewayServiceConstants;
import com.assignment.gatewayservice.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthTokenService {

	@Value("${jwt.webtoken.secret}")
	private String secreString;
	
	public String generateToken(User user) {
		return createToken(Map.of(GatewayServiceConstants.LOGGED_IN_USER_ID, user.getUserId(),
				GatewayServiceConstants.USER_ROLE, user.getUserRole()), user.getEmail());
	}

	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(getExpirationTime()).signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Date getExpirationTime() {
		return new Date(System.currentTimeMillis() + 1000 * 60 * 30);
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secreString);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
