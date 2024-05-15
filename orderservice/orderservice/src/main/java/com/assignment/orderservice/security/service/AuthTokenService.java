package com.assignment.orderservice.security.service;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthTokenService {

	@Value("${jwt.webtoken.secret}")
	private String secreString;

	/* *
	 * Function responsible for validating JWT token and getting the payload from JWT token
	 * */
	public Claims validateToken(final String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	/* *
	 * Function responsible for generating signing key for verification of JWT token
	 * */
	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secreString);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
