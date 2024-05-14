package com.assignment.userservice.security.service;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.userservice.constants.GatewayServiceConstants;
import com.assignment.userservice.util.JedisClientHelper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

	@Autowired
	private JedisClientHelper jedisClientHelper;

	@Autowired
	private AuthTokenService authTokenService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(
				(HttpServletRequest) request);
		String requestString = IOUtils.toString(cachedBodyHttpServletRequest.getReader());
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(requestString);
			log.debug("Parsed request is {} ", jsonObject);
			if (jsonObject.has("token")) {
				if (Boolean.parseBoolean(jedisClientHelper.getValue(jsonObject.getString("token")))) {
					if (GatewayServiceConstants.CUSTOMER_USER_ROLE
							.equalsIgnoreCase(authTokenService.validateToken(jsonObject.getString("token"))
									.get(GatewayServiceConstants.USER_ROLE).toString())) {
						jedisClientHelper.extendTokenLife(jsonObject.getString("token"));
						chain.doFilter(cachedBodyHttpServletRequest, response);
					} else {
						log.error("Not a valid role for request {}", jsonObject);
						((HttpServletResponse) response).sendError(401, "Only registered Customers are allowed");
					}
				} else {
					log.error("Token expired for request {}", jsonObject);
					((HttpServletResponse) response).sendError(401, "Token Expired");
				}
			} else {
				log.error("No token provided for request {}", jsonObject);
				((HttpServletResponse) response).sendError(401, "Token not provided");
			}
		} catch (Exception ex) {
			((HttpServletResponse) response).sendError(400, "Invalid JSON structure");
		}
	}
}
