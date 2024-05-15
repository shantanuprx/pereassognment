package com.assignment.gatewayservice.constants;
/* *
 * Class for managing constants related to authentication and Authorization.
 * */
public class GatewayServiceConstants {

	private GatewayServiceConstants() {

	}
	public static final String MD5 = "MD5";
	public static final String GATEWAY_SERVICE = "GATEWAY_SERVICE";
	public static final String ACTIVE_FLAG = "A";
	public static final String RECORD_CREATION_MESSAGE = "User successfully created";
	public static final String LOCKED_ACCOUNT_STATUS = "L";
	public static final String LOGGED_IN_USER_ID = "loggedInUserId";
	public static final String USER_ROLE = "userRole";
	public static final String ADMIN_USER_ROLE = "ADMIN";
	public static final String CUSTOMER = "CUSTOMER";
	public static final String INVALID_SERVICE_NAME = "Invalid Service name or URL";
	public static final String INVALID_PAYLOAD = "Invalid request payload";
	public static final String TOKEN = "token";
	public static final String TOKEN_EXPIRED = "Token Expired";
	public static final String TOKEN_NOT_AVAILABLE = "Token not provided";
	public static final String EMAIL_ALREADY_REGISTERED = "Email id already registered ";
	public static final String INVALID_CREDS = "Invalid Credentials";
	public static final String ACCOUNT_LOCKED = "Account Locked";
	public static final String INVALID_USER_NAME_OR_PASSWORD = "Invalid Username/Password";
}
