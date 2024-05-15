package com.assignment.productservice.auth;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import com.assignment.productservice.constants.GatewayServiceConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenGenService {

	public static String generateToken(int userId, String userRole, String emailId, String secretToken) {
		return createToken(
				Map.of(GatewayServiceConstants.LOGGED_IN_USER_ID, userId, GatewayServiceConstants.USER_ROLE, userRole),
				emailId, secretToken);
	}

	private static String createToken(Map<String, Object> claims, String userName, String secretString) {
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(getExpirationTime()).signWith(getSignKey(secretString), SignatureAlgorithm.HS256)
				.compact();
	}

	private static Date getExpirationTime() {
		return new Date(System.currentTimeMillis() + 1000 * 60 * 30);
	}

	private static Key getSignKey(String secretString) {
		byte[] keyBytes = Decoders.BASE64.decode(secretString);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
