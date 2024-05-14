package com.assignment.ecommerceweb.security.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthTokenService {

	@Value("${jwt.webtoken.secret}")
	private String secreString;

	public Claims validateToken(final String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	public String generateToken(String userName) {
		return createToken(new HashMap<>(), userName);
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
