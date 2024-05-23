package com.assignment.productservice.security.service;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.productservice.constants.GatewayServiceConstants;
import com.assignment.productservice.util.JedisClientHelper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of web filter. Responsible for checking whether token is valid
 * or not.
 */
@Slf4j
@Component
public class AuthFilter implements Filter {

	@Autowired
	private JedisClientHelper jedisClientHelper;

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
					jedisClientHelper.extendTokenLife(token);
					chain.doFilter(cachedBodyHttpServletRequest, response);
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
