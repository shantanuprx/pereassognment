package com.assignment.orderservice.security.service;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.orderservice.constants.GatewayServiceConstants;
import com.assignment.orderservice.util.JedisClientHelper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Implementation of web filter. Responsible for checking whether token is valid or not.
 */
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
			String token = cachedBodyHttpServletRequest.getHeader(GatewayServiceConstants.TOKEN);
			if (token != null) {
				if (Boolean.parseBoolean(jedisClientHelper.getValue(token))) {
					if (GatewayServiceConstants.CUSTOMER_USER_ROLE.equalsIgnoreCase(
							authTokenService.validateToken(token).get(GatewayServiceConstants.USER_ROLE).toString())) {
						jedisClientHelper.extendTokenLife(token);
						chain.doFilter(cachedBodyHttpServletRequest, response);
					} else {
						log.error("Not a valid role for request {}", jsonObject);
						((HttpServletResponse) response).sendError(401,
								GatewayServiceConstants.ONLY_REGISTERED_CUSTOMERS_ALLOWED);
					}
				} else {
					log.error("Token expired for request {}", jsonObject);
					((HttpServletResponse) response).sendError(401, GatewayServiceConstants.TOKEN_EXPIRED);
				}
			} else {
				log.error("No token provided for request {}", jsonObject);
				((HttpServletResponse) response).sendError(401, GatewayServiceConstants.TOKEN_NOT_AVAILABLE);
			}
		} catch (Exception ex) {
			((HttpServletResponse) response).sendError(400, "Invalid JSON structure");
		}
	}
}
