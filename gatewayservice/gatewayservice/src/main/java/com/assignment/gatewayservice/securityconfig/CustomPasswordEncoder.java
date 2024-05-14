package com.assignment.gatewayservice.securityconfig;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.assignment.gatewayservice.util.HashUtil;

public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		try {
			return HashUtil.generateMd5Hash(rawPassword.toString());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			return HashUtil.generateMd5Hash(rawPassword.toString()).equals(encodedPassword);
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
	}

}
