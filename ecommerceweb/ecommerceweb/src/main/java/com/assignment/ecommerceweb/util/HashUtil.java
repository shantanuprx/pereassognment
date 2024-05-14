package com.assignment.ecommerceweb.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.assignment.ecommerceweb.constants.GatewayServiceConstants;

public class HashUtil {

	public static String generateMd5Hash(String password) throws NoSuchAlgorithmException {
		MessageDigest md5Instance = MessageDigest.getInstance(GatewayServiceConstants.MD5);
		md5Instance.update(password.getBytes());
		byte[] digest = md5Instance.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String md5hash = bigInt.toString(16);
		while (md5hash.length() < 32) {
			md5hash = "0" + md5hash;
		}
		return md5hash;
	}
	
	public static String generateRandomId() {
		return UUID.randomUUID().toString();
	}
}
